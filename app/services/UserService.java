package services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.google.inject.Singleton;
import models.User;

import java.util.List;

@Singleton
public final class UserService extends BaseService {

    public User findUserByEmail(String email) {
        return Ebean.find(User.class).where().and(Expr.eq("email", email), Expr.eq("isActive", true)).findUnique();
    }

    public User findUserById(int userId) {
        return Ebean.find(User.class)
                .where().and(Expr.eq("id", userId), Expr.eq("isActive", true)).findUnique();
    }

    public List<User> findActiveUsersForAccount(int accountId) {
        return Ebean.find(User.class).where()
                .eq("account.id", accountId)
                .eq("isActive", true).findList();
    }
}
