package dev.ari.explorebuejpa.repository;

import dev.ari.explorebuejpa.model.Difficulty;
import dev.ari.explorebuejpa.model.Tour;
import dev.ari.explorebuejpa.model.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, String> {

    List<Tour> findByDifficulty(Difficulty difficulty);

    List<Tour> findByTourPackage_Code(String tourPackageCode);


}
