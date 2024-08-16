package com.buihuuduy.identity.repository;

import com.buihuuduy.identity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    boolean existsByUsername(String username);
    User findByUsername(String username);
}
