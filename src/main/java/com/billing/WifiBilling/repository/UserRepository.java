package com.billing.WifiBilling.repository;

import com.billing.WifiBilling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByUsername(String userName);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    //Count the total number of users; for analytics and dashboard features
    long count();
    package com.billing.WifiBilling.repository;

 
 
}
