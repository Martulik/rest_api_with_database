package main.web;

import main.entity.User;
import main.repository.UserRepository;
import main.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder pwdEncoded;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/singin")
    public ResponseEntity singIn(@RequestBody AuthRequest request) {
        try {
            String name = request.getUserName();
            String token = jwtTokenProvider.createToken(
                    name,
                    userRepository.findUserByUserName(name)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found")).getRoles()
            );
            Optional<User> user = userRepository.findUserByUserName(name);
            if (!pwdEncoded.matches(request.getPassword(), user.get().getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }
            Map<Object, Object> model = new HashMap<>();
            model.put("userName", name);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("/validate")
    public String validate(String token) {
        if (jwtTokenProvider.validateToken(token)) {
            return "successfully authentication";
        } else {
            throw new BadCredentialsException("Authentication fail");
        }
    }

    @PostMapping("/registration")
    public String registration(@RequestBody AuthRequest request) {
        try {
            if (userRepository.findUserByUserName(request.getUserName()).isPresent()) {
                throw new RuntimeException("Such a user already exists");
            } else {
                userRepository.save(new User(request.getUserName(), pwdEncoded.encode(request.getPassword()),
                        Collections.singletonList("ROLE_USER")));
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username");
        }
        return "successful registration";
    }
}
