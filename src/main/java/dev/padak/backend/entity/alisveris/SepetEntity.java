package dev.padak.backend.entity.alisveris;

import dev.padak.backend.entity.kullanici.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "SEPET")
public class SepetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kullanici_id")
    private UserEntity kullanici;

    @ManyToOne
    @JoinColumn(name = "kitap_id")
    private KitapEntity kitap;

    @Column(name = "adet")
    private int adet;

}
