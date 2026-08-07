package com.todo.todoapp.services;

import com.todo.todoapp.details.UserInfoDetails;
import com.todo.todoapp.models.User;
import com.todo.todoapp.repositories.UserRepository;
import com.todo.todoapp.requests.RegisterRequest;
import com.todo.todoapp.responses.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username or password incorrect"));
        return new UserInfoDetails(user);
    }

    public RegisterResponse registerUser(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.firstName());
        user.setSurname(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        User createdUser = userRepository.save(user);

        return new RegisterResponse(
                createdUser
        );
    }

}
