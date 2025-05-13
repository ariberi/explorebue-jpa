package dev.ari.explorebuejpa.repository;

import dev.ari.explorebuejpa.model.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourPackageRepository extends JpaRepository<TourPackage, String> {

    Optional<TourPackage> findByName(String name);

}
