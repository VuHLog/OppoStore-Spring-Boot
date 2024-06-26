package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    boolean existsByUsername(String username);

    Optional<Users> findByUsername(String username);

    Page<Users> findByUsernameContainsIgnoreCase(String username, Pageable pageable);


}