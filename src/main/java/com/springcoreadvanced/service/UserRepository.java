package com.springcoreadvanced.service;

import com.springcoreadvanced.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.name = :name")
    User findUserByName(String name);
}
