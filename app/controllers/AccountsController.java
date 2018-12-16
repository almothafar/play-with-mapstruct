package controllers;

import com.google.inject.Inject;
import controllers.dto.AccountDTO;
import mappers.AccountMapper;
import mappers.UserMapper;
import models.Account;
import models.User;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.Result;
import services.AccountService;

import java.io.IOException;
import java.util.ArrayList;


public class AccountsController extends BaseController {
    private final AccountService accountService;

    @Inject
    public AccountsController(AccountService accountService, MessagesApi messagesApi) {
        super(messagesApi);
        this.accountService = accountService;
    }

    public Result getAccountsList() {
        return ok(Json.toJson(AccountMapper.INSTANCE.map(accountService.findListOfAccounts())));
    }

    public Result getAccountDetailsById(int accountId) throws IOException {
        Account account = accountService.getAccountById(accountId);
        AccountDTO accountDTO = AccountMapper.INSTANCE.map(account);
        accountDTO.setUsers(UserMapper.INSTANCE.map(new ArrayList<User>(account.getUsers())));
        return ok(Json.toJson(accountDTO));
    }

}
