package dev.padak.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class productDTO {

    private int id;
    private String aciklama;
    private int adet;
    private double fiyat;
}
