package dev.padak.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MesajDTO {

    private String kod;
    private String aciklama;
}
