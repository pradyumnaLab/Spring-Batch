package com.pk.batch.BatchDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pk.batch.BatchDemo.model.User;


public interface UserDao extends JpaRepository<User, Integer>{

}
