package controllers.dto;

import java.util.List;


public class ProductDTO extends BaseDTO {

    private String name;
    private String description;
    private List<ProjectDTO> projects;

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public ProductDTO setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
        return this;
    }
}
