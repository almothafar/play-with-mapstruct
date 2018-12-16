package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "accounts")
public class Account extends BaseModel<Account> {

    @Column(length = 128, nullable = false)
    @Constraints.Required
    @Constraints.MinLength(2)
    @Constraints.MaxLength(128)
    private String name;

    @Column(length = 500)
    @Constraints.MinLength(0)
    @Constraints.MaxLength(500)
    private String notes;

    @Column
    @Constraints.Min(1)
    private int usersLimit = 5;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<AccountProject> projects;

    @Transient
    public int getUsersCount() {
        return getUsers().size();
    }

    @Transient
    public Set<User> getAdmins() {
        return getUsers().stream().filter(User::isAdmin).collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Account setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public int getUsersLimit() {
        return usersLimit;
    }

    public Account setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
        return this;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Account setUsers(Set<User> users) {
        this.users = users;
        return this;
    }

    public Set<AccountProject> getProjects() {
        return projects;
    }

    public Account setProjects(Set<AccountProject> projects) {
        this.projects = projects;
        return this;
    }
}
