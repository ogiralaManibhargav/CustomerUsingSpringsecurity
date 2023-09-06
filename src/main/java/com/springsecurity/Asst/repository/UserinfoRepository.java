package com.springsecurity.Asst.repository;

import com.springsecurity.Asst.entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserinfoRepository extends JpaRepository<Userinfo,Long> {
    Optional<Userinfo> findByName(String username);
}
