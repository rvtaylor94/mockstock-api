package com.example.mockstock.users;

import com.example.mockstock.portfolios.Portfolios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    public User getUserByID(Long id) { return userRepository.findById(id).orElse(null); }
    public User createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
        } else {
            throw new UserNotFound();
        }
    }
    public User updateUser(Long id, Double balance) {
        Optional<User> update = userRepository.findById(id);
        if (update.isPresent()) {
            update.get().setBalance(balance);
            return userRepository.save(update.get());
        }
        return null;
    }
}
