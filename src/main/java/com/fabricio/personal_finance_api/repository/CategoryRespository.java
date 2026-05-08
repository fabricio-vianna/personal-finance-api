package com.fabricio.personal_finance_api.repository;

import com.fabricio.personal_finance_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository<Category, Long> {
}
