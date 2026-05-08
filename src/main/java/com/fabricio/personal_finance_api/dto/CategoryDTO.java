package com.fabricio.personal_finance_api.dto;

import com.fabricio.personal_finance_api.entity.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Long id;
    private String name;
    private CategoryType type;
    private Long userId;
}
