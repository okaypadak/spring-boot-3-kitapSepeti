package dev.padak.backend.dto.kullanici;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private boolean basarili;
    private String token;
    private String aciklama;
}
