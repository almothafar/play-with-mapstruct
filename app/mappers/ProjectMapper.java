package mappers;

import controllers.dto.ProjectDTO;
import models.Project;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(config = CentralConfig.class, uses = {AccountMapper.class, ProductMapper.class})
public interface ProjectMapper {
    public static ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mappings({
            @Mapping(target = "product", source = "product")
    })
    Project map(ProjectDTO productDTO);

    @InheritInverseConfiguration
    ProjectDTO map(Project product);

    List<Project> map(ArrayList<ProjectDTO> productDTOS);

    List<ProjectDTO> map(List<Project> products);

    List<ProjectDTO> map(Set<Project> products);
}


