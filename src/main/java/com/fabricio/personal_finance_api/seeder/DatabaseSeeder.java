package com.fabricio.personal_finance_api.seeder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import com.fabricio.personal_finance_api.model.entity.Category;
import com.fabricio.personal_finance_api.model.entity.Transaction;
import com.fabricio.personal_finance_api.model.entity.User;
import com.fabricio.personal_finance_api.model.entity.enums.CategoryType;
import com.fabricio.personal_finance_api.model.entity.enums.TransactionType;
import com.fabricio.personal_finance_api.model.entity.enums.UserRole;
import com.fabricio.personal_finance_api.repository.CategoryRespository;
import com.fabricio.personal_finance_api.repository.TransactionRepository;
import com.fabricio.personal_finance_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRespository categoryRespository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {

        transactionRepository.deleteAll();
        categoryRespository.deleteAll();
        userRepository.deleteAll();

        // USERS
        User user1 = new User(null, "Fabricio Vianna", "fabricio@email.com", "123456", null, new ArrayList<>(), new ArrayList<>(), UserRole.ADMIN);
        User user2 = new User(null, "Maria Oliveira", "maria@email.com", "654321", null, new ArrayList<>(), new ArrayList<>(), UserRole.USER);
        User user3 = new User(null, "João Souza", "joao@email.com", "111222", null, new ArrayList<>(), new ArrayList<>(), UserRole.USER);

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

        // CATEGORIES
        Category catSalary = new Category(null, "Salário", CategoryType.INCOME, user1);
        Category catFood = new Category(null, "Alimentação", CategoryType.EXPENSE, user1);
        Category catFreelance = new Category(null, "Freelance", CategoryType.INCOME, user2);
        Category catTransport = new Category(null, "Transporte", CategoryType.EXPENSE, user2);
        Category catHealth = new Category(null, "Saúde", CategoryType.EXPENSE, user3);
        Category catInvest = new Category(null, "Investimentos", CategoryType.INCOME, user3);
        Category catEntertainment = new Category(null, "Lazer", CategoryType.EXPENSE, user1);

        categoryRespository.saveAll(Arrays.asList(
                catSalary, catFood, catFreelance, catTransport,
                catHealth, catInvest, catEntertainment
        ));

        // TRANSACTIONS
        Transaction t1 = new Transaction(null, "Salário mês maio", new BigDecimal("5200.00"),
                TransactionType.INCOME,
                LocalDateTime.of(2026, 5, 1, 9, 0),
                LocalDateTime.of(2026, 5, 1, 9, 0),
                user1, catSalary);

        Transaction t2 = new Transaction(null, "Supermercado extra", new BigDecimal("320.40"),
                TransactionType.EXPENSE,
                LocalDateTime.of(2026, 5, 2, 18, 30),
                LocalDateTime.of(2026, 5, 2, 18, 30),
                user1, catFood);

        Transaction t3 = new Transaction(null, "Projeto site cliente", new BigDecimal("2500.00"),
                TransactionType.INCOME,
                LocalDateTime.of(2026, 5, 3, 14, 0),
                LocalDateTime.of(2026, 5, 3, 14, 0),
                user2, catFreelance);

        Transaction t4 = new Transaction(null, "Uber semanal", new BigDecimal("89.90"),
                TransactionType.EXPENSE,
                LocalDateTime.of(2026, 5, 4, 8, 15),
                LocalDateTime.of(2026, 5, 4, 8, 15),
                user2, catTransport);

        Transaction t5 = new Transaction(null, "Consulta médica", new BigDecimal("400.00"),
                TransactionType.EXPENSE,
                LocalDateTime.of(2026, 5, 5, 10, 0),
                LocalDateTime.of(2026, 5, 5, 10, 0),
                user3, catHealth);

        Transaction t6 = new Transaction(null, "Dividendos ações", new BigDecimal("150.75"),
                TransactionType.INCOME,
                LocalDateTime.of(2026, 5, 6, 11, 0),
                LocalDateTime.of(2026, 5, 6, 11, 0),
                user3, catInvest);

        Transaction t7 = new Transaction(null, "Cinema e lanche", new BigDecimal("120.00"),
                TransactionType.EXPENSE,
                LocalDateTime.of(2026, 5, 7, 20, 0),
                LocalDateTime.of(2026, 5, 7, 20, 0),
                user1, catEntertainment);

        Transaction t8 = new Transaction(null, "Freelance extra app", new BigDecimal("900.00"),
                TransactionType.INCOME,
                LocalDateTime.of(2026, 5, 8, 16, 30),
                LocalDateTime.of(2026, 5, 8, 16, 30),
                user2, catFreelance);

        Transaction t9 = new Transaction(null, "Mercado do mês", new BigDecimal("780.55"),
                TransactionType.EXPENSE,
                LocalDateTime.of(2026, 5, 9, 19, 0),
                LocalDateTime.of(2026, 5, 9, 19, 0),
                user3, catFood);

        Transaction t10 = new Transaction(null, "Salário bônus", new BigDecimal("1200.00"),
                TransactionType.INCOME,
                LocalDateTime.of(2026, 5, 10, 9, 0),
                LocalDateTime.of(2026, 5, 10, 9, 0),
                user1, catSalary);

        transactionRepository.saveAll(Arrays.asList(
                t1, t2, t3, t4, t5,
                t6, t7, t8, t9, t10
        ));
    }
}
