package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "products")
public class Product extends BaseModel<Product> {

    @Column(length = 256, nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MinLength(2)
    @Constraints.MaxLength(256)
    private String name;

    @Column(length = 1000)
    @Constraints.MaxLength(1000)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Project> projects;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Product setProjects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }
}
