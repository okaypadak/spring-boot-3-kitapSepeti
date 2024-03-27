package dev.padak.backend.entity.kullanici;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ROL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROL_ISMI")
    private String rol;

}