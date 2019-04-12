package com.microservives.movieinfoservice.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservives.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoResources {

	@RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
       // MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return new Movie(movieId, "MANIKARNIKA", "A struggle by rani Lakshmibai to wake India");

    }
	
	
}
