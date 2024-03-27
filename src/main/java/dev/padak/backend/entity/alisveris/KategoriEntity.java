package dev.padak.backend.entity.alisveris;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kategori")
public class KategoriEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;

    @ManyToMany(mappedBy = "kategoriler")
    private Set<KitapEntity> kitaplar = new HashSet<>();

}
