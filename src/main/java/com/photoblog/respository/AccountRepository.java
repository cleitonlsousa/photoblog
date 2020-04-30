package com.photoblog.respository;

import com.photoblog.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

        Boolean existsByEmail(String email);
        Account findByEmail(String email);
}
