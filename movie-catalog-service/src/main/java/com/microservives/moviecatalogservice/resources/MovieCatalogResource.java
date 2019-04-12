package com.microservives.moviecatalogservice.resources;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservives.moviecatalogservice.models.CatalogItem;
import com.microservives.moviecatalogservice.models.Movie;
import com.microservives.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


	
	  @Autowired 
	  private RestTemplate restTemplate;
	  
	/*
	 * @Autowired private DiscoveryClient discoveryClient;
	 */
	  
	 // @Autowired WebClient.Builder webClientBuilder;
	 
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
    		
    	 //RestTemplate restTemplate = new RestTemplate();
    	//WebClient.Builder webClientBuilder
    	
    	UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
    	
    	return userRating.getRatings().stream().map(rating -> {
    		// for each movie id call movie info service and get details
    		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
    		// put them all together
    		return new CatalogItem(movie.getName(),"Jhansi Ki Rani", rating.getRating());
    	}).collect(Collectors.toList());
		/*
		 * return Collections.singletonList( new CatalogItem("MANIKARNIKA",
		 * "Jhansi Ki Rani", 4));
		 */
		/*
		 * UserRating userRating =
		 * restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" +
		 * userId, UserRating.class);
		 * 
		 * return userRating.getRatings().stream() .map(rating -> { Movie movie =
		 * restTemplate.getForObject("http://movie-info-service/movies/" +
		 * rating.getMovieId(), Movie.class); return new CatalogItem(movie.getName(),
		 * movie.getDescription(), rating.getRating()); })
		 * .collect(Collectors.toList());
		 */

    }
}
