package com.ajustadoati.backend.adapter.rest.mapper;

import com.ajustadoati.backend.adapter.rest.dto.request.LocationDto;
import com.ajustadoati.backend.domain.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto toDto(Location location);
}
