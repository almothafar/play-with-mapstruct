package controllers.viewModels;


import java.util.Date;
import java.util.List;

public class AccountDTO extends BaseDTO {

    private String name;
    private String notes;
    private int usersLimit;
    private int usersCount;
    private Date expirationDate;
    private String adminEmail;
    private List<UserDTO> users;

    public String getName() {
        return name;
    }

    public AccountDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public AccountDTO setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public int getUsersLimit() {
        return usersLimit;
    }

    public AccountDTO setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
        return this;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public AccountDTO setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public AccountDTO setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
        return this;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public AccountDTO setUsers(List<UserDTO> users) {
        this.users = users;
        return this;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public AccountDTO setUsersCount(int usersCount) {
        this.usersCount = usersCount;
        return this;
    }

}
