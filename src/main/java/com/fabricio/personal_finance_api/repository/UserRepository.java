package com.fabricio.personal_finance_api.repository;

import com.fabricio.personal_finance_api.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email) throws UsernameNotFoundException;
}
