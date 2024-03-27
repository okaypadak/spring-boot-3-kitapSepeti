package dev.padak.backend.controller.kullanici;

import dev.padak.backend.dto.MesajDTO;
import dev.padak.backend.dto.request.SepetRequestDTO;
import dev.padak.backend.dto.sepet.SepetDTO;
import dev.padak.backend.dto.sepet.SepetKitapListesiDTO;
import dev.padak.backend.service.kullanici.SepetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alisveris")
@Slf4j
public class AlisverisController {

    @Autowired
    SepetService sepetService;

    @GetMapping("/sepet/{userId}")
    public ResponseEntity<SepetDTO> sepet(@PathVariable Long userId) {
        return ResponseEntity.ok(sepetService.sepetListesi(userId));
    }

    @PostMapping("/sepet/ekle")
    public ResponseEntity<SepetDTO> ekle(@RequestBody SepetRequestDTO request) {

        SepetDTO sepetDTO = new SepetDTO();

        try {
            sepetDTO = sepetService.ekle(request);
            sepetDTO.setSonuc("Başarılı");
            sepetDTO.setAciklama("Kitap eklendi sepet güncellendi");
        } catch (Exception e) {
            sepetDTO.setSonuc("Hata!");
            sepetDTO.setAciklama(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(sepetDTO);
    }

    @PostMapping("/sepet/cikar")
    public ResponseEntity<SepetDTO> cikar(@RequestBody SepetRequestDTO request) {

        SepetDTO sepetDTO = new SepetDTO();


        return ResponseEntity.ok(sepetDTO);
    }

    @PostMapping("/sepet/odeme")
    public ResponseEntity<MesajDTO> odeme(@RequestBody SepetRequestDTO request) {

        return ResponseEntity.ok(MesajDTO.builder().kod("").aciklama("").build());
    }
}
