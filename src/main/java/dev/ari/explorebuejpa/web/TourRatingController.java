package dev.ari.explorebuejpa.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import dev.ari.explorebuejpa.businesslogic.TourRatingService;

import java.util.NoSuchElementException;

/**
 * Tour Rating Controller
 *
 * Created by Mary Ellen Bowman
 */
@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
    private TourRatingService tourRatingService;

    public TourRatingController(TourRatingService tourRatingService) {
        this.tourRatingService = tourRatingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody @Valid RatingDto ratingDto) {
        this.tourRatingService.createNew(tourId, ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String return404(NoSuchElementException e) {
        return e.getMessage();
    }



}
