package com.fabricio.personal_finance_api.dto;

import java.io.Serial;
import java.io.Serializable;

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
public class CategoryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1656597925266535726L;

    private Long id;
    private String name;
    private CategoryType type;
    private Long userId;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.type = category.getType();
        this.userId = category.getUser().getId();
    }
}
