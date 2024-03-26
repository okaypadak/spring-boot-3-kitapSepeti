package dev.padak.backend.entity.kullanici;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "KULLANICI_ROL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserRolEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "KULLANICI_ID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ROL_ID")
    private RolEntity rol;

    @Override
    public String getAuthority() {
        return rol.getRolIsmi();
    }
}