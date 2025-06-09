package com.ajustadoati.backend.adapter.rest.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SearchDto(
    String query,
    Integer categoryId,
    Integer userId,
    BigDecimal latitude,
    BigDecimal longitude
) {}
