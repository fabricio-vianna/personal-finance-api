package com.fabricio.personal_finance_api.dto;

import com.fabricio.personal_finance_api.entity.Category;
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

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.type = category.getType();
        this.userId = category.getUserId();
    }
}
