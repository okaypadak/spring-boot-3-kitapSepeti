package dev.padak.backend.controller.kullanici;

import dev.padak.backend.dto.kitap.KitapDTO;
import dev.padak.backend.service.kullanici.KitapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitap")
public class KitapController {

    @Autowired
    KitapService kitapService;

    @PreAuthorize("hasRole('KULLANICI')")
    @GetMapping("/listesi")
    public ResponseEntity<List<KitapDTO>> liste() {
        return ResponseEntity.ok(kitapService.tumListe());
    }
}