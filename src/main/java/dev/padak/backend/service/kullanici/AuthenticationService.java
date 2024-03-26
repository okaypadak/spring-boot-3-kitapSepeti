package dev.padak.backend.service.kullanici;

import dev.padak.backend.dto.kullanici.AuthenticationResponse;
import dev.padak.backend.dto.kullanici.LoginRequest;
import dev.padak.backend.dto.kullanici.RegisterRequest;
import dev.padak.backend.entity.kullanici.RolEntity;
import dev.padak.backend.entity.kullanici.UserEntity;
import dev.padak.backend.entity.kullanici.UserRolEntity;
import dev.padak.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final RolService rolService;


    public AuthenticationResponse register(RegisterRequest registerRequest) {

        List<RolEntity> roller = null;

        if(!userService.findByUsernameCheck(registerRequest.getKullaniciAdi())) {

        try {
            roller = registerRequest.getYetkiler().stream()
                    .map(rolDto -> rolService.gonder(rolDto.getRolIsmi()).orElseThrow(() -> new RuntimeException("Böyle bir rol yok")))
                    .toList();
        } catch (RuntimeException e) {
            return AuthenticationResponse.builder()
                    .aciklama("Böyle bir rol yok")
                    .basarili(false)
                    .build();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setAdi(registerRequest.getAdi());
        userEntity.setSoyadi(registerRequest.getSoyadi());
        userEntity.setKullaniciAdi(registerRequest.getKullaniciAdi());
        userEntity.setEposta(registerRequest.getEposta());
        userEntity.setSifre(passwordEncoder.encode(registerRequest.getSifre()));
        userEntity.setYetkiler(roller.stream().map(rol -> {
            UserRolEntity userRolEntity = new UserRolEntity();
            userRolEntity.setRol(rol);
            return userRolEntity;
        }).collect(Collectors.toList()));

        userService.save(userEntity);

        String token = jwtService.generatetoken(userEntity);

        return AuthenticationResponse.builder()
                .token(token)
                .basarili(true)
                .build();

        } else {
            return AuthenticationResponse.builder()
                    .basarili(false)
                    .aciklama("Kullanıcı ismi kullanılmış")
                    .build();
        }
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserEntity userEntity = userService.findByUsername(loginRequest.getUsername());
        String token = jwtService.generatetoken(userEntity);
        return AuthenticationResponse.builder()
                .token(token)
                .basarili(true)
                .build();
    }

    public UserEntity getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userService.findByUsername(currentUserName);
        }
        throw new UsernameNotFoundException("User not found");
    }

}
