package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new ru.kata.spring.boot_security.demo.models.UserDetails(user.get());
    }
}
