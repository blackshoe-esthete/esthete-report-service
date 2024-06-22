package org.example.blackshoe.esthetereportservice.repository;

import org.example.blackshoe.esthetereportservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
