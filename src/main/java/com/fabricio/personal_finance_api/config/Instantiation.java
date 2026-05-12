package com.fabricio.personal_finance_api.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import com.fabricio.personal_finance_api.entity.Category;
import com.fabricio.personal_finance_api.entity.Transaction;
import com.fabricio.personal_finance_api.entity.User;
import com.fabricio.personal_finance_api.entity.enums.CategoryType;
import com.fabricio.personal_finance_api.entity.enums.TransactionType;
import com.fabricio.personal_finance_api.repository.CategoryRespository;
import com.fabricio.personal_finance_api.repository.TransactionRepository;
import com.fabricio.personal_finance_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRespository categoryRespository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        categoryRespository.deleteAll();
        transactionRepository.deleteAll();

        User user1 = new User(null, "Fabricio Vianna", "fabricio@email.com", "123456", null, new ArrayList<>(), new ArrayList<>());
        User user2 = new User(null, "Maria Oliveira", "maria@email.com", "654321", null, new ArrayList<>(), new ArrayList<>());

        userRepository.saveAll(Arrays.asList(user1, user2));

        Category category1 = new Category(null, "Salário", CategoryType.INCOME, user1);
        Category category2 = new Category(null, "Alimentação", CategoryType.EXPENSE, user1);
        Category category3 = new Category(null, "Freelance", CategoryType.INCOME, user2);
        Category category4 = new Category(null, "Transporte", CategoryType.EXPENSE, user2);

        categoryRespository.saveAll(Arrays.asList(category1, category2, category3, category4));

        Transaction transaction1 = new Transaction(null, "Pagamento mensal", new BigDecimal("5000.00"), TransactionType.INCOME, LocalDateTime.of(2026, 5, 12, 10, 0), LocalDateTime.of(2026, 5, 12, 10, 0), user1, category1.getId());
        Transaction transaction2 = new Transaction(null, "Compra no mercado", new BigDecimal("250.75"), TransactionType.EXPENSE, LocalDateTime.of(2026, 5, 12, 12, 0), LocalDateTime.of(2026, 5, 12, 12, 0), user1, category2.getId());
        Transaction transaction3 = new Transaction(null, "Projeto freelancer", new BigDecimal("1800.00"), TransactionType.INCOME, LocalDateTime.of(2026, 5, 12, 14, 0), LocalDateTime.of(2026, 5, 12, 14, 0), user2, category3.getId());

        transactionRepository.saveAll(Arrays.asList(transaction1, transaction2, transaction3));
    }
}
