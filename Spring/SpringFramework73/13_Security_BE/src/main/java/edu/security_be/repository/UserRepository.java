package edu.security_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

//    @Query(value = "select * from User where id=?1", nativeQuery = true)
//    Optional<User> findbyusername(String username);
//
////    custom update query
//    @Modifying
//    @Query(value = "select * from User where id=?1", nativeQuery = true)
//    Optional<User> findbyusername(String username);
}
