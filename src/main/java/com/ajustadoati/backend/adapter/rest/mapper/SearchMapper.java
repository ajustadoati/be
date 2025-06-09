package com.ajustadoati.backend.adapter.rest.mapper;

import com.ajustadoati.backend.adapter.rest.dto.SearchDto;
import com.ajustadoati.backend.domain.SearchRequest;
import com.ajustadoati.backend.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SearchMapper {

    @Mapping(target = "categoryId", source = "searchRequest.category.id")
    @Mapping(target = "userId", source = "searchRequest.user", qualifiedByName = "nullToUser")
    SearchDto toDto(SearchRequest searchRequest);

    @Named("nullToUser")
    default Integer nullToUser(User user) {
        if (user == null) {
            return 0;
        }
        return user.getUserId();
    }

}
