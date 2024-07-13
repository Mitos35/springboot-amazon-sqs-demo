package my.code.awssqsdemo.mapper;

import my.code.awssqsdemo.dto.RecipientDto;
import my.code.awssqsdemo.entity.RecipientEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipientMapper {

    RecipientDto map(RecipientEntity entity);

    @InheritInverseConfiguration
    RecipientEntity map(RecipientDto dto);
}
