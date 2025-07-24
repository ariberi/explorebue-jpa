package dev.ari.explorebuejpa.businesslogic;

import org.springframework.stereotype.Service;

import dev.ari.explorebuejpa.model.Difficulty;
import dev.ari.explorebuejpa.model.Region;
import dev.ari.explorebuejpa.model.Tour;
import dev.ari.explorebuejpa.model.TourPackage;
import dev.ari.explorebuejpa.repository.TourPackageRepository;
import dev.ari.explorebuejpa.repository.TourRepository;

import java.util.List;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class TourService {
    private TourPackageRepository tourPackageRepository;
    private TourRepository tourRepository;

    public TourService(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
        this.tourPackageRepository = tourPackageRepository;
        this.tourRepository = tourRepository;
    }

    public Tour createTour(String tourPackageName, String title,
                           String description, String blurb, Integer price, String duration,
                           String bullets, String keywords, Difficulty difficulty, Region region) {
        log.info("Create tour {} for package {}", title, tourPackageName);
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
                .orElseThrow(() -> new RuntimeException("Tour Package not found for id:" + tourPackageName));
        return tourRepository.save(new Tour(title, description, blurb,
                price, duration, bullets, keywords, tourPackage, difficulty, region));
    }

    public List<Tour> lookupByDifficulty(Difficulty difficulty) {
        log.info("Lookup tours by difficulty {}", difficulty);
        return tourRepository.findByDifficulty(difficulty);
    }

    public List<Tour> lookupByPackage(String tourPackageCode) {
        log.info("Lookup tour by code {}", tourPackageCode);
        return tourRepository.findByTourPackageCode(tourPackageCode);
    }

    public long total() {
        log.info("Get total tours");
        return tourRepository.count();
    }
}