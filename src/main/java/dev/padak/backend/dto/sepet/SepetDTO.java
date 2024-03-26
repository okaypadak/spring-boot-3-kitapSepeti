package dev.padak.backend.dto.sepet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SepetDTO {
    private List<SepetKitapListesiDTO> kitapListesi;
    private BigDecimal toplamTutar;
    private String sonuc;
    private String aciklama;
}
