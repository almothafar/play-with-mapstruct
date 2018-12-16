package utils;

import com.google.inject.Inject;
import com.typesafe.config.Config;
import io.ebean.annotation.Transactional;
import models.Account;
import play.Logger;
import services.AccountService;
import services.UserService;

import java.util.List;

public class DataUtils {
    private final AccountService accountService;
    private final UserService userService;

    @Inject
    private DataUtils(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Transactional
    public Account seedAccount(Config configuration) {
        String adminEmail = configuration.getString(AppConstants.INITIAL_ACCOUNT_ADMIN_EMAIL);
        List<Account> accounts = accountService.findListOfAccounts();

        if (accounts.size() == 0) {
            Logger.warn("Initial account not found, will new seed one");
            String password = configuration.getString(AppConstants.INITIAL_ACCOUNT_ADMIN_PASSWORD);
            String firstName = configuration.getString(AppConstants.INITIAL_ACCOUNT_ADMIN_FIRST_NAME);
            String lastName = configuration.getString(AppConstants.INITIAL_ACCOUNT_ADMIN_LAST_NAME);
            String accountName = configuration.getString(AppConstants.INITIAL_ACCOUNT_NAME);

            Account account = accountService.signup(adminEmail, accountName, firstName, lastName);
            account.setUsersLimit(10).update();
            userService.findActiveUsersForAccount(account.getId()).get(0).setPasswordEncrypted(password).update();
            return account;
        } else {
            return userService.findUserByEmail(adminEmail).getAccount();
        }

    }
}
