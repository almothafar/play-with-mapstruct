package services;


import exceptions.BusinessException;
import io.ebean.Ebean;
import models.Account;
import models.User;
import play.api.i18n.Lang;
import play.i18n.MessagesApi;
import utils.MsgKeys;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

@Singleton
public final class AccountService {

    private final UserService userService;
    private final MessagesApi messagesApi;

    @Inject
    public AccountService(UserService userService, MessagesApi messagesApi) {
        this.userService = userService;
        this.messagesApi = messagesApi;
    }

    public List<Account> findListOfAccounts() {
        return Ebean.find(Account.class).where().findList();
    }

    public Account getAccountById(int accountId) {
        return Ebean.find(Account.class)
                .where().eq("id", accountId).findOne();
    }

    public Account signup(String email, String accountName, String firstName, String lastName) {
        User oldUser = userService.findUserByEmail(email);

        if (oldUser == null) {
            int usersLimit = 5;
            int trialDays = 30;

            Account newAccount = new Account();
            newAccount.setName(accountName)
                    .setUsersLimit(usersLimit).save();

            User user = addFirstUserAsAdminForAccount(newAccount, firstName, lastName, email);
//            newAccount.getUsers().add(user);
            return newAccount;
        }
        throw new BusinessException(messagesApi.get(Lang.defaultLang(), MsgKeys.ACCOUNT_EMAIL_EXIST));
    }

    private User addFirstUserAsAdminForAccount(Account parentAccount, String firstName, String lastName, String email) {
        User newUser = new User();
        newUser.setFirstName(firstName).setLastName(lastName).setEmail(email).setAdmin(true);
        String password = UUID.randomUUID().toString().substring(0, 8).replace("-", "%");
        newUser = addUserForAccount(parentAccount, newUser.setPassword(password));
        return newUser;
    }

    public User addUserForAccount(Account parentAccount, User user) {
        user.setPasswordEncrypted(user.getPassword());
        user.setAccount(parentAccount);
        user.save();
        return user;
    }

    public void checkAccountsExpiry() {
        List<Account> accounts = findListOfAccounts();
    }
}
