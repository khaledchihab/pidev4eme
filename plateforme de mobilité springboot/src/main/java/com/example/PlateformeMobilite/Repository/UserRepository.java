package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);
    public User findByEmail(String email);
    List<User> findAllByUserId(Long userId);


    Boolean existsByEmail(String email);
    @Query("SELECT DISTINCT fd.userId, f.formId " +
            "FROM FormData fd " +
            "JOIN fd.field.form f")
    List<Object[]> findUsersAndForms();
}
