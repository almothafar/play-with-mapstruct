package services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.google.inject.Singleton;
import exceptions.BusinessException;
import models.Account;
import models.User;
import org.joda.time.DateTime;
import utils.AppLogger;
import utils.AppUtils;
import utils.MsgKeys;

import java.util.List;
import java.util.UUID;

@Singleton
public final class AccountService extends BaseService {

    public List<Account> findListOfAccounts() {
        return Ebean.find(Account.class).where().findList();
    }

    public Account getAccountById(int accountId) {
        return Ebean.find(Account.class)
                .where().eq("id", accountId).findUnique();
    }

    public Account signup(String email, String accountName, String firstName, String lastName) {
        User oldUser = getUserService().findUserByEmail(email);

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
        throw new BusinessException(getMessage(MsgKeys.ACCOUNT_EMAIL_EXIST));
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
        updateUsersCountForAccount(parentAccount);
        return user;
    }

    public synchronized void updateUsersCountForAccount(Account account) {
        account.setUsersCount(getUserService().findActiveUsersForAccount(account.getId()).size());
        account.update();
    }

    public void checkAccountsExpiry() {
        List<Account> accounts = findListOfAccounts();
    }
}
