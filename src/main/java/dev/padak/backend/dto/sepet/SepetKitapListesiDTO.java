package dev.padak.backend.dto.sepet;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SepetKitapListesiDTO {
    private Long id;
    private String kitapAdi;
    private int adet;
    private BigDecimal tutar;
}
