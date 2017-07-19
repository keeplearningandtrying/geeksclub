package com.sivalabs.geeksclub.repositories;

import com.sivalabs.geeksclub.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
