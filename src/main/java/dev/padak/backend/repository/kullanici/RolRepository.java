package dev.padak.backend.repository.kullanici;

import dev.padak.backend.entity.kullanici.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {

    Optional<RolEntity> findByRol (String adi);

}
