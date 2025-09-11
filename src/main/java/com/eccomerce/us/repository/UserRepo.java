package com.eccomerce.us.repository;

import com.eccomerce.us.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
