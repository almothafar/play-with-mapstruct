package services;


import com.google.inject.Singleton;
import io.ebean.Ebean;
import io.ebean.Expr;
import models.User;

import java.util.List;

@Singleton
public final class UserService {

    public User findUserByEmail(String email) {
        return Ebean.find(User.class).where().and(Expr.eq("email", email), Expr.eq("isActive", true)).findOne();
    }

    public User findUserById(int userId) {
        return Ebean.find(User.class)
                .where().and(Expr.eq("id", userId), Expr.eq("isActive", true)).findOne();
    }

    public List<User> findActiveUsersForAccount(int accountId) {
        return Ebean.find(User.class).where()
                .eq("account.id", accountId)
                .eq("isActive", true).findList();
    }
}
