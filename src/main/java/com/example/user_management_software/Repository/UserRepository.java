package com.example.user_management_software.Repository;

import com.example.user_management_software.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
    List<User> findUserByUsernameAndPassword(String username, String password);
    @Query("select u from User u where u.email = ?1")
    User byemail(String email);
    @Query("select u from User u where u.role = ?1")
    List<User> bySpecificRole(String role);
    @Query("select u from User u where u.age >=?1")
    List<User> bySpecificAgeOrAbove(Integer age);
}
