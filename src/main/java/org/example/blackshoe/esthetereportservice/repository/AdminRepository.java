package org.example.blackshoe.esthetereportservice.repository;

import org.example.blackshoe.esthetereportservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String userEmail);

    @Query("SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password")
    Optional<Admin> findByEmailAndPassword(String email, String password);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Admin a WHERE a.email = :email AND a.password = :password")
    boolean adminExistsByEmailAndPassword(String email, String password);
}
