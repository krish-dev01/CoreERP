package com.coreerp.coreerp.repository;

import com.coreerp.coreerp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // later we will add custom queries here
    User findByUsername(String username);
}
