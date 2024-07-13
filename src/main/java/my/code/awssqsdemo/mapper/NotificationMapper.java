package my.code.awssqsdemo.mapper;

import my.code.awssqsdemo.dto.NotificationDto;
import my.code.awssqsdemo.entity.NotificationEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDto map(NotificationEntity entity);

    @InheritInverseConfiguration
    NotificationEntity map(NotificationDto dto);
}
