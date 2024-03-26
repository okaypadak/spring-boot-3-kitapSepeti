package dev.padak.backend.service.kullanici;

import dev.padak.backend.dto.kitap.KitapDTO;
import dev.padak.backend.entity.alisveris.KitapEntity;
import dev.padak.backend.repository.kullanici.KitapRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitapService {

    @Autowired
    private KitapRepository kitapRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void kaydet(KitapEntity kitap) {
        kitapRepository.save(kitap);
    }

    public void sil(Long id) {
        Optional<KitapEntity> optionalKitap = kitapRepository.findById(id);
        if (optionalKitap.isPresent()) {
            KitapEntity kitap = optionalKitap.get();
            kitapRepository.delete(kitap);
        } else {
            throw new IllegalArgumentException("Kitap bulunamadı: " + id);
        }
    }

    public List<KitapDTO> tumListe(){
        return kitapRepository.findAll().stream().map(tek -> modelMapper.map(tek, KitapDTO.class)).toList();
    }

    public Optional<KitapEntity> getirKitap(Long id) {
        return kitapRepository.findById(id);
    }

}
