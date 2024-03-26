package dev.padak.backend.dto.kullanici;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String adi;
    private String soyadi;
    private String kullaniciAdi;
    private String eposta;
    private String sifre;
    private List<UserRolDTO> yetkiler;
}
