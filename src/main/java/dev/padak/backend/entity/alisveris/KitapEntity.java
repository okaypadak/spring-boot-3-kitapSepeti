package dev.padak.backend.entity.alisveris;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "KITAP")
public class KitapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "KITAP_ADI")
    private String adi;
    @Column(name = "YAZAR")
    private String yazar;
    @Column(name = "FIYAT")
    private BigDecimal fiyat;
}
