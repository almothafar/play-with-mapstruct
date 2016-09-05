package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
import utils.AppUtils;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User extends BaseModel {
    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JsonBackReference
    private Account account;

    @Column(length = 35, nullable = false)
    @Constraints.Required
    @Constraints.MinLength(2)
    @Constraints.MaxLength(50)
    private String firstName;

    @Column(length = 35, nullable = false)
    @Constraints.Required
    @Constraints.MinLength(2)
    @Constraints.MaxLength(50)
    private String lastName;

    @Column(length = 256)
    @Constraints.MaxLength(256)
    private String jobTitle;

    @Column(length = 16)
    @Constraints.MinLength(5)
    @Constraints.MaxLength(16)
    private String mobile;

    @Column(length = 256, unique = true, nullable = false)
    @Constraints.MinLength(4)
    @Constraints.MaxLength(256)
    @Constraints.Required
    @Constraints.Email
    private String email;

    @Column
    private boolean isAdmin = false;

    @Column(length = 64, nullable = false)
    @Constraints.Required
    @Constraints.MinLength(6)
    @Constraints.MaxLength(64)
    @JsonIgnore
    private String password;

    @Column
    private boolean passwordUpdated = false;


    public User() {
    }

    public String getFullName() {
        return AppUtils.concatStrings(getFirstName(), " ", getLastName());
    }

    public User setPasswordEncrypted(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public User setAccount(Account account) {
        this.account = account;
        return this;
    }

    public boolean isPasswordUpdated() {
        return passwordUpdated;
    }

    public User setPasswordUpdated(boolean passwordUpdated) {
        this.passwordUpdated = passwordUpdated;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public User setAdmin(boolean admin) {
        this.isAdmin = admin;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public User setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public User setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
}
