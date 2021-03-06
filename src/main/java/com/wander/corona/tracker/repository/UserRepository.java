package com.wander.corona.tracker.repository;

import com.wander.corona.tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer > {
    User findByEmail(String email);
}