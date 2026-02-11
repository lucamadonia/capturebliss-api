package com.capturebliss.api.repo;

import com.capturebliss.api.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
  Optional<User> findUserByEmail(String email);
}
