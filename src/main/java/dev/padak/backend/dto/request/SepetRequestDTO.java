package dev.padak.backend.dto.request;

import lombok.Data;

@Data
public class SepetRequestDTO {
    private Long kullaniciId;
    private Long kitapId;
    private Integer adet;
}
