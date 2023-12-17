package com.company.repository;

import com.company.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTable, String> {
}
