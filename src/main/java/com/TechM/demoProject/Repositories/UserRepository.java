package com.TechM.demoProject.Repositories;

import com.TechM.demoProject.Models.Users;
import com.TechM.demoProject.Services.MobileNumberAlreadyExistsException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository  extends JpaRepository<Users, Integer> {
    boolean existsByMobileNumber(String mobileNumber);
}
