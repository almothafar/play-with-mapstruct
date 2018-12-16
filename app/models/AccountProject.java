package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
@Table(name = "account_project",
        uniqueConstraints = @UniqueConstraint(columnNames = {"account_id", "project_id"}))
public class AccountProject extends BaseModel<AccountProject> {

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    protected Account account;

    @ManyToOne(targetEntity = Project.class, fetch = FetchType.LAZY)
    @JsonBackReference
    private Project project;

    public Account getAccount() {
        return account;
    }

    public AccountProject setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public AccountProject setProject(Project project) {
        this.project = project;
        return this;
    }
}
