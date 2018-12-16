package modules;

import akka.actor.ActorSystem;
import akka.actor.Cancellable;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.Config;
import io.ebean.annotation.Transactional;
import models.Account;
import play.Logger;
import play.inject.ApplicationLifecycle;
import scala.concurrent.duration.Duration;
import services.AccountService;
import utils.DataUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Singleton
public class OnStartup {

    private final List<Cancellable> cancellableSchedules = new ArrayList<>();


    @Inject
    public OnStartup(final ActorSystem system,
                     final Config configuration,
                     final DataUtils utils,
                     final ApplicationLifecycle applicationLifecycle,
                     final AccountService accountService) {

        createAccount(configuration, utils);
        startSchedulers(system, accountService);
        initStopHook(applicationLifecycle);
    }

    private void startSchedulers(final ActorSystem system, final AccountService accountService) {

        cancellableSchedules.add(system.scheduler().schedule(
                Duration.create(nextExecutionInMillis(LocalDateTime.now().getDayOfWeek().getValue()), TimeUnit.MILLISECONDS),
                Duration.create(24, TimeUnit.HOURS),
                accountService::checkAccountsExpiry,
                system.dispatcher()
        ));

        Logger.info("Schedulers started");
    }

    @Transactional
    private void createAccount(final Config configuration, final DataUtils utils) {
        Account account = utils.seedAccount(configuration);
    }

    private long nextExecutionInMillis(int dayOfWeek) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime dayOfWeekWithTime = LocalDate.now().with(WeekFields.of(Locale.getDefault()).dayOfWeek(), dayOfWeek).atTime(LocalTime.MIDNIGHT).plusHours(8);
        if (currentDateTime.isBefore(dayOfWeekWithTime)) {
            return ChronoUnit.MILLIS.between(currentDateTime, dayOfWeekWithTime);
        } else {
            return ChronoUnit.MILLIS.between(currentDateTime, dayOfWeekWithTime.plusWeeks(1));
        }
    }

    private void initStopHook(ApplicationLifecycle lifecycle) {
        lifecycle.addStopHook(() -> {
            Logger.warn("Application restarting, cancelling Scheduled tasks");
            cancellableSchedules.forEach(Cancellable::cancel);
            return CompletableFuture.completedFuture(null);
        });
    }
}