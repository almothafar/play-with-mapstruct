package controllers.dto;

public class ProjectDTO extends BaseDTO {

    private ProductDTO product;
    private String name;

    public ProductDTO getProduct() {
        return product;
    }

    public ProjectDTO setProduct(ProductDTO product) {
        this.product = product;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectDTO setName(String name) {
        this.name = name;
        return this;
    }
}
