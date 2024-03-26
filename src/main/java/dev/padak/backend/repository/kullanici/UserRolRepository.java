package dev.padak.backend.repository.kullanici;

import dev.padak.backend.entity.kullanici.UserRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolRepository extends JpaRepository<UserRolEntity, Long> {
}
