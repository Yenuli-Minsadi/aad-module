package edu.security_be.service;

import edu.security_be.dto.AuthDTO;
import edu.security_be.dto.AuthResponseDTO;
import edu.security_be.dto.RegisterDTO;
import edu.security_be.entity.Role;
import edu.security_be.repository.UserRepository;
import edu.security_be.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    public AuthResponseDTO authenticate(AuthDTO authDTO) {
        //find user from db
         User user = userRepository.findByUsername(authDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(authDTO.getUsername()));//authdto eken username ek hoynw hmbune nattn exception throw krnw, hmbunoth user eke tiygnnw
         //match passwords(db and request pwds)
        if (!passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
             throw new BadCredentialsException("Wrong password");
         }
        //if all okay generate new token
        String token = jwtUtil.generateToken(authDTO.getUsername());
        return new AuthResponseDTO(token);

    }

    public String register(RegisterDTO registerDTO) {
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already in use");
        }
        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.valueOf(RegisterDTO.getRole))
                .build();
        userRepository.save(user);
        return "Üser registered successfully";
    }
}
