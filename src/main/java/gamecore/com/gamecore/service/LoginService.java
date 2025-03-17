package gamecore.com.gamecore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Login;
import gamecore.com.gamecore.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean validateUser(String loginname, String password) 
    {
        Optional<Login> user = loginRepository.findByLoginname(loginname);
        
        return user.isPresent() && new BCryptPasswordEncoder().matches(password, user.get().getPassword());
    }
}
