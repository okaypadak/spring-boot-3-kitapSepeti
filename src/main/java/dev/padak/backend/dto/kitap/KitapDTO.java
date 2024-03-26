package dev.padak.backend.dto.kitap;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class KitapDTO {
    private Long id;
    private String adi;
    private String yazar;
    private BigDecimal fiyat;
}
