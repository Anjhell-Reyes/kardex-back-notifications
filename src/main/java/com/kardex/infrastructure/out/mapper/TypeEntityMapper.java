package com.kardex.infrastructure.out.mapper;

import com.kardex.domain.model.Type;
import com.kardex.infrastructure.out.entity.TypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TypeEntityMapper {
    Type toType(TypeEntity type);

}
