package dev.ari.explorebuejpa.businesslogic;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.ari.explorebuejpa.model.TourPackage;
import dev.ari.explorebuejpa.repository.TourPackageRepository;

@Service
public class TourPackageService {
    private TourPackageRepository tourPackageRepository;

    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
        return this.tourPackageRepository.findById(code)
                .orElse(this.tourPackageRepository.save(new TourPackage(code, name)));
    }

    public List<TourPackage> lookupAll() {
        return this.tourPackageRepository.findAll();
    }

    public long total() {
        return this.tourPackageRepository.count();
    }
}
