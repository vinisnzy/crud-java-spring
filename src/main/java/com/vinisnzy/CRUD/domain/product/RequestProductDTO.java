package com.vinisnzy.CRUD.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProductDTO(Long id, @NotBlank String name, @NotNull Double price) {
}
