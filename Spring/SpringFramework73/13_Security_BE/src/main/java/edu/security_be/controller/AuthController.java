package edu.security_be.controller;

import edu.security_be.dto.APIResponse;
import edu.security_be.dto.AuthDTO;
import edu.security_be.dto.RegisterDTO;
import edu.security_be.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(new APIResponse(
                200,"OK",authService.register(registerDTO)
        ));
    }

    @PostMapping("login")
    public ResponseEntity<APIResponse> loginUser(@RequestBody AuthDTO authDTO){
        return ResponseEntity.ok(new APIResponse(
                200, "OK", authService.authenticate(authDTO)
        ));
    }
}
