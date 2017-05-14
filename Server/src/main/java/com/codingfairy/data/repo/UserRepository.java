package com.codingfairy.data.repo;

import com.codingfairy.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User data repository
 * @author cuihao
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByUsername(String username);
}
