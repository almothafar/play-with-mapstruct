package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account extends BaseModel {

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

    @Column
    @Constraints.Min(0)
    private int usersCount = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public Account setUsers(Set<User> users) {
        this.users = users;
        return this;
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

    public int getUsersCount() {
        return usersCount;
    }

    public Account setUsersCount(int usersCount) {
        this.usersCount = usersCount;
        return this;
    }
}
