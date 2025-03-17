package gamecore.com.gamecore.repository;

import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
        
    Optional<Login> findByLoginname(String loginname);
}
