package dev.ari.explorebuejpa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ari.explorebuejpa.businesslogic.TourPackageService;
import dev.ari.explorebuejpa.businesslogic.TourService;

import dev.ari.explorebuejpa.model.Difficulty;
import dev.ari.explorebuejpa.model.Region;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ExplorebueJpaApplication implements CommandLineRunner {

    private final String TOUR_IMPORT_FILE = "ExploreBuenosAires.json";

    private final TourPackageService tourPackageService;

    private final TourService tourService;

    public ExplorebueJpaApplication(TourPackageService tourPackageService, TourService tourService) {
        this.tourPackageService = tourPackageService;
        this.tourService = tourService;
    }

    public static void main(String[] args) {

        SpringApplication.run(ExplorebueJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createTourAllPackages();
        System.out.println("Persisted Packages = " + tourPackageService.total());
        createToursFromFile(TOUR_IMPORT_FILE);
        System.out.println("Persisted Tours = " + tourService.total());

        /********* CHALLENGES **********/
        System.out.println("\n\nEasy Tours");
        tourService.lookupByDifficulty(Difficulty.Easy).forEach(System.out::println);

        System.out.println("\n\nUrban BA Tours");
        tourService.lookupByPackage("UB").forEach(System.out::println);
    }

    /**
     * Initialize all the known tour packages
     */
    private void createTourAllPackages() {
        tourPackageService.createTourPackage("UB", "Urban BA");
        tourPackageService.createTourPackage("TN", "Tango Nights");
        tourPackageService.createTourPackage("GE", "Green Escape");
        tourPackageService.createTourPackage("FB", "Flavors BA");
        tourPackageService.createTourPackage("RLP", "Río de La Plata");
        tourPackageService.createTourPackage("NB", "Nightlife BA");
        tourPackageService.createTourPackage("NW", "Nature Walks");
        tourPackageService.createTourPackage("FF", "Family Fun");
    }



    /**
     * Create tour entities from an external file
     */
    private void createToursFromFile(String fileToImport) throws IOException {
        TourFromFile.read(fileToImport).forEach(t ->
                tourService.createTour(
                        t.packageName(),
                        t.title(),
                        t.description(),
                        t.blurb(),
                        t.price(),
                        t.length(),
                        t.bullets(),
                        t.keywords(),
                        Difficulty.valueOf(t.difficulty()),
                        Region.findByLabel(t.region())
                )
        );
    }


    record TourFromFile(String packageName, String title, String description,
                        String blurb, Integer price, String length, String bullets,
                        String keywords, String difficulty, String region) {
        static List<TourFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper().readValue(new File(fileToImport),
                    new TypeReference<>() {
                    });
        }
    }

}
