package dev.padak.backend.entity.kullanici;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="KULLANICI")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "KULLANICI_ADI")
    private String kullaniciAdi;

    @Column(name = "ADI")
    private String adi;

    @Column(name = "SOYADI")
    private String soyadi;

    @Column(name = "SIFRE")
    private String sifre;

    @Column(name = "EPOSTA")
    private String eposta;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "KULLANICI_ID")
    private List<UserRolEntity> yetkiler;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return yetkiler.stream()
                .map(tek -> new SimpleGrantedAuthority(tek.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sifre;
    }

    @Override
    public String getUsername() {
        return kullaniciAdi;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
