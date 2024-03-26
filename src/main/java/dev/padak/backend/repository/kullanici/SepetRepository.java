package dev.padak.backend.repository.kullanici;

import dev.padak.backend.entity.alisveris.KitapEntity;
import dev.padak.backend.entity.alisveris.SepetEntity;
import dev.padak.backend.entity.kullanici.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SepetRepository extends JpaRepository<SepetEntity, Long> {

    List<SepetEntity> findByKullaniciId(Long kullaniciId);
    Boolean existsByKullaniciIdAndKitapId(Long kullaniciId, Long kitapId);
}
