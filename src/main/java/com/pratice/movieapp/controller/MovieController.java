package com.pratice.movieapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pratice.movieapp.model.Movie;
import com.pratice.movieapp.service.MovieService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired

	MovieService movieService;

	@PostMapping(value = "", consumes = "application/json")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> insertMovie(@RequestBody Movie movie) {
        log.info("Inside InsertMovie - Post");
		try {
			 Movie savedMovie = movieService.insertMovie(movie);
			 return new ResponseEntity<>(savedMovie,HttpStatus.CREATED);
		} catch (Exception e) {
	       
			String errorMessage=e.getMessage();
			e.printStackTrace();
			return new ResponseEntity<>( errorMessage,HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Movie> getAllMovies() {
		log.info("Inside GetAllMovies - Get");
		return movieService.getAllMovies();

	}
	
	@GetMapping(value="/search", produces="application/json")
	public List<Movie> findMovieByName(@RequestParam (name = "movieName",required = false) String name, 
			@RequestParam (name = "actorName",required = false) String actorName) {
		if (name!=null && actorName==null) {
			  List<Movie> movieList =  movieService.findMovieByName(name);
			    return movieList ;
		}else if(name==null && actorName!=null){
			  List<Movie> movieList = movieService.findMovieByActorName(actorName);
			  return movieList;
		
		}
		else if(name!=null && actorName!=null){
			  List<Movie> movieList = movieService.findMovieByNameAndActorName(name, actorName);
			  return movieList;
		
		}
		log.info("Inside FindAllByName - Get");
	    return null;
		
	}
	
	
	
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void deleteMovie(@PathVariable("id") Long id ) {
		log.info("Inside delete/id - Delete");
		 movieService.deleteMovie(id);

	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie ) {
		log.info("Inside update movie - Put");
		try {
		 Movie updateMovie = movieService.updateMovie(movie);
			return new ResponseEntity<>( updateMovie,HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage=e.getMessage();
			e.printStackTrace();
			return new ResponseEntity<>( errorMessage,HttpStatus.BAD_REQUEST);
		}

	}
	
//	@GetMapping(value = "/{id}", produces = "application/json")
//	public Movie getMovieById(@PathVariable("id") Long id ) {
//		log.info("Inside delete/id - Get");
//		return movieService.getMovieById(id);
//
//	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> getMovieById(@PathVariable("id") Long id ) {
		log.info("Inside Get Movie by id - Get");
		 Movie movieById;
		try {
			movieById = movieService.getMovieById(id);
			 if (movieById!= null) {
				 return new ResponseEntity<>(movieById, HttpStatus.OK);
			 }
			 else {
				 return new ResponseEntity<>( HttpStatus.NOT_FOUND);
			 }
			
		} catch (Exception e) {
			String errorMessage=e.getMessage();
			e.printStackTrace();
			return new ResponseEntity<>( errorMessage,HttpStatus.BAD_REQUEST);
		}
		

	}
	
	
	
}
