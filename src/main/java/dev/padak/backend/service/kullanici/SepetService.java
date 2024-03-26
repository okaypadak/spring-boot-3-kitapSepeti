package dev.padak.backend.service.kullanici;

import dev.padak.backend.controller.kullanici.KullaniciController;
import dev.padak.backend.dto.request.SepetRequestDTO;
import dev.padak.backend.dto.sepet.SepetDTO;
import dev.padak.backend.dto.sepet.SepetKitapListesiDTO;
import dev.padak.backend.entity.alisveris.SepetEntity;
import dev.padak.backend.repository.kullanici.SepetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SepetService {

    @Autowired
    private SepetRepository sepetRepository;

    @Autowired
    private KitapService kitapService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    public SepetDTO ekle(SepetRequestDTO request){

        if(sepetRepository.existsByKullaniciIdAndKitapId(request.getKullaniciId(), request.getKitapId())) {

            List<SepetEntity> sepetListesi = sepetRepository.findByKullaniciId(request.getKullaniciId());

            Optional<SepetEntity> sepetKitap = sepetListesi.stream().filter(tek -> Objects.equals(tek.getKitap().getId(), request.getKitapId())).findFirst();
            sepetKitap.get().setAdet(sepetKitap.get().getAdet() + request.getAdet());
            sepetRepository.save(sepetKitap.get());

        } else {

            SepetEntity sepet = new SepetEntity();
            sepet.setAdet(request.getAdet());
            sepet.setKitap(kitapService.getirKitap(request.getKitapId()).orElseThrow(() -> new RuntimeException("Böyle bir kitap yok")));
            sepet.setKullanici(userService.getirKullaniciId(request.getKullaniciId()).orElseThrow(() -> new RuntimeException("Böyle bir kullanıcı yok")));

            sepetRepository.save(sepet);
        }

        return sepetListesi(request.getKullaniciId());

    }

    public SepetDTO sepetListesi(Long id){

        List<SepetKitapListesiDTO> kitapListesi = sepetRepository.findByKullaniciId(id).stream().map(tek -> {

            SepetKitapListesiDTO sepetKitapListesiDTO = new SepetKitapListesiDTO();
            sepetKitapListesiDTO.setId(tek.getId());
            sepetKitapListesiDTO.setAdet(tek.getAdet());
            sepetKitapListesiDTO.setTutar(BigDecimal.valueOf(tek.getAdet()).multiply(tek.getKitap().getFiyat()));
            sepetKitapListesiDTO.setKitapAdi(tek.getKitap().getAdi());
            return sepetKitapListesiDTO;

        }).toList();

        BigDecimal toplamTutar = BigDecimal.ZERO;

        for (SepetKitapListesiDTO sepetKitapListesiDTO : kitapListesi) {
            toplamTutar = toplamTutar.add(sepetKitapListesiDTO.getTutar());
        }

        return SepetDTO.builder().kitapListesi(kitapListesi).toplamTutar(toplamTutar).build();

    }
}
