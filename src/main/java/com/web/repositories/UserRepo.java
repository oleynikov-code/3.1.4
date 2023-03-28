package com.web.repositories;

import com.web.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

        @Query("Select u from User u left join fetch u.roles where u.email=:email")
        public User findByEmail(String email);
}
