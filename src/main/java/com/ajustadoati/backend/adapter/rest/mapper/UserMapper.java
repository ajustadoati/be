package com.ajustadoati.backend.adapter.rest.mapper;

import com.ajustadoati.backend.adapter.rest.dto.response.UserResponseDto;
import com.ajustadoati.backend.domain.Category;
import com.ajustadoati.backend.domain.Role;
import com.ajustadoati.backend.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "stringToRole")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "stringToCategory")
    @Mapping(target = "locations", source = "locations")
    UserResponseDto toDto(User user);

    @Named("stringToRole")
    default Set<String> stringToRole(Set<Role> roles) {
        return roles.stream()
            .map(Role::getRoleName)
            .collect(Collectors.toSet());
    }

    @Named("stringToCategory")
    default Set<String> stringToCategory(Set<Category> categories) {
        if(categories==null){
            return Set.of();
        }
        return categories.stream()
            .map(Category::getName)
            .collect(Collectors.toSet());
    }
}
