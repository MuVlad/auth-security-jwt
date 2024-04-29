package com.muslimov.vlad.authsecurityjwt.repository;

import com.muslimov.vlad.authsecurityjwt.exception.model.NotFoundException;
import com.muslimov.vlad.authsecurityjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new NotFoundException("User with id: " + id + " not found!")
        );
    }

    Optional<User> findFirstByUsername(String name);

    Optional<User> findFirstByEmail(String email);
}
