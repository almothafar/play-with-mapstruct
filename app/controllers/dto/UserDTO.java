package controllers.dto;


public class UserDTO extends BaseDTO {

    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String mobile;
    private String jobTitle;
    private boolean isAdmin;
    private boolean passwordUpdated;
    private AccountDTO account;

    public boolean isPasswordUpdated() {
        return passwordUpdated;
    }

    public UserDTO setPasswordUpdated(boolean passwordUpdated) {
        this.passwordUpdated = passwordUpdated;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserDTO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public UserDTO setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public UserDTO setAdmin(boolean admin) {
        isAdmin = admin;
        return this;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public UserDTO setAccount(AccountDTO account) {
        this.account = account;
        return this;
    }

    public static class LocalUserDTO extends UserDTO {

    }

    public static class TwitterUserDTO extends UserDTO {
        private String screenName;

        public String getScreenName() {
            return screenName;
        }

        public TwitterUserDTO setScreenName(String screenName) {
            this.screenName = screenName;
            return this;
        }
    }

    public static class FacebookUserDTO extends UserDTO {
        private String userId;

        public String getUserId() {
            return userId;
        }

        public FacebookUserDTO setUserId(String userId) {
            this.userId = userId;
            return this;
        }
    }
}
