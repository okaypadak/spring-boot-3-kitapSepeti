package dev.padak.backend.controller.kullanici;

import dev.padak.backend.dto.kullanici.AuthenticationResponse;
import dev.padak.backend.dto.kullanici.LoginRequest;
import dev.padak.backend.dto.kullanici.RegisterRequest;
import dev.padak.backend.service.kullanici.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class KullaniciController {

    @Autowired
    private AuthenticationService authenticationService;

    private Logger logger = LoggerFactory.getLogger(KullaniciController.class);


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

}
