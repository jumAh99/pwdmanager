package com.passwordmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.passwordmanager.domain.User;

@Repository
public interface PwdManagerRepository extends JpaRepository<User, Integer> {
    List<User> findByUsername(String keyword);
}
