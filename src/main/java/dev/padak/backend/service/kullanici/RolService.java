package dev.padak.backend.service.kullanici;

import dev.padak.backend.entity.kullanici.RolEntity;
import dev.padak.backend.repository.kullanici.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    public Optional<RolEntity> gonder(String rolIsmi) {

        return rolRepository.findByRolIsmi(rolIsmi);
    }

    
}
