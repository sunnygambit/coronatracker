package com.wander.corona.tracker.repository;

import com.wander.corona.tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long > {
    User findByEmail(String email);
}