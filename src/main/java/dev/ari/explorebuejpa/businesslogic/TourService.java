package dev.ari.explorebuejpa.businesslogic;

import org.springframework.stereotype.Service;

import dev.ari.explorebuejpa.model.Difficulty;
import dev.ari.explorebuejpa.model.Region;
import dev.ari.explorebuejpa.model.Tour;
import dev.ari.explorebuejpa.model.TourPackage;
import dev.ari.explorebuejpa.repository.TourPackageRepository;
import dev.ari.explorebuejpa.repository.TourRepository;

import java.util.List;


@Service
public class TourService {

  private TourPackageRepository tourPackageRepository;
  private TourRepository tourRepository;

  public TourService(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
      this.tourPackageRepository = tourPackageRepository;
      this.tourRepository = tourRepository;
  }

  public Tour createTour(String tourPackageName, String title, String description, String blurb, Integer price, String duration,
                         String bullets, String keywords, Difficulty difficulty, Region region) {

      TourPackage tourPackage = this.tourPackageRepository.findByName(tourPackageName)
            .orElseThrow(() -> new RuntimeException("Tour package not found for id: " + tourPackageName));

      return this.tourRepository
              .save(new Tour(title, description, blurb, price, duration,
                      bullets, keywords, tourPackage, difficulty, region));
  }

  public List<Tour> lookupByDifficulty(Difficulty difficulty) {
      return this.tourRepository.findByDifficulty(difficulty);
  }

  public List<Tour> lookupByPackage(String tourPackageCode) {
      return this.tourRepository.findByTourPackage_Code(tourPackageCode);
  }



  public long total() {
      return this.tourRepository.count();
  }

}
