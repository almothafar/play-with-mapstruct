package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import play.data.validation.Constraints;

import javax.persistence.*;


@Entity
@Table(name = "projects",
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "name"}))
public class Project extends BaseModel<Project> {

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    private Product product;

    @Column(length = 256, nullable = false)
    @Constraints.Required
    @Constraints.MinLength(2)
    @Constraints.MaxLength(256)
    private String name;

    public Product getProduct() {
        return product;
    }

    public Project setProduct(Product product) {
        this.product = product;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }
}
