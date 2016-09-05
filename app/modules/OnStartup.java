package modules;

import akka.actor.ActorSystem;
import akka.actor.Cancellable;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Account;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalTime;
import play.Application;
import play.Configuration;
import play.Environment;
import play.Logger;
import play.db.ebean.Transactional;
import play.inject.ApplicationLifecycle;
import scala.concurrent.duration.Duration;
import services.AccountService;
import utils.AppConstants;
import utils.DataUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Singleton
public class OnStartup {

    private final List<Cancellable> cancellableSchedules = new ArrayList<>();
    private final int offsetFromMidnightInHours;
    private final int firstDay;


    @Inject
    public OnStartup(final ActorSystem system,
                     final Configuration configuration,
                     final Application application,
                     final DataUtils utils,
                     final ApplicationLifecycle applicationLifecycle,
                     final AccountService accountService,
                     final Environment environment) {

        this.firstDay = configuration.getInt(AppConstants.FIRST_DAY_OF_WEEK_KEY);
        // TODO should be in application.conf ?
        // don't make it pass 23 hours its from 0 - 23 within the day this is why (n % 24)
        this.offsetFromMidnightInHours = 0 % 24;

        createAccount(configuration, utils);
        startSchedulers(system, accountService);
        initStopHook(applicationLifecycle);
    }

    private void startSchedulers(final ActorSystem system, final AccountService accountService) {

        cancellableSchedules.add(system.scheduler().schedule(
                Duration.create(nextExecutionInMillis(DateTime.now().getDayOfWeek()), TimeUnit.MILLISECONDS),
                Duration.create(24, TimeUnit.HOURS),
                accountService::checkAccountsExpiry,
                system.dispatcher()
        ));

        Logger.info("Schedulers started");
    }

    @Transactional
    private void createAccount(final Configuration configuration, final DataUtils utils) {
        Account account = utils.seedAccount(configuration);
    }

    private long nextExecutionInMillis(int dayOfWeek) {
        DateTime now = DateTime.now();
        LocalTime time = LocalTime.now();
        // closest time in day ignore the value of seconds and millis
        DateTime closest = time.toDateTime(now).minuteOfDay().withMinimumValue().withDayOfWeek(dayOfWeek).plusHours(offsetFromMidnightInHours);
        return new Interval(now, closest.isBefore(now) ? closest.plusWeeks(1) : closest).toDurationMillis();
    }

    private void initStopHook(ApplicationLifecycle lifecycle) {
        lifecycle.addStopHook(() -> {
            Logger.warn("Application restarting, cancelling Scheduled tasks");
            cancellableSchedules.forEach(Cancellable::cancel);
            return CompletableFuture.completedFuture(null);
        });
    }
}