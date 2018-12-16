package mappers;

import controllers.dto.ProductDTO;
import models.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(config = CentralConfig.class, uses = {ProjectMapper.class})
public interface ProductMapper {
    public static ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target = "projects", source = "projects", ignore = true),
    })
    Product map(ProductDTO productDTO);

    @InheritInverseConfiguration
    ProductDTO map(Product product);

    List<Product> map(ArrayList<ProductDTO> productDTOS);

    List<ProductDTO> map(List<Product> products);
}


