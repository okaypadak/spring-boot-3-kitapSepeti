package dev.padak.backend.repository.kullanici;

import dev.padak.backend.entity.alisveris.KitapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitapRepository extends JpaRepository<KitapEntity, Long> {

}
