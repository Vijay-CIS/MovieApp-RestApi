package com.pratice.movieapp.service;


import java.util.List;

import com.pratice.movieapp.model.Movie;


public interface MovieService {
   
	
    public Movie insertMovie(Movie m) throws Exception;

    public List<Movie> getAllMovies();

	public void deleteMovie(Long id);

	public Movie updateMovie(Movie m) throws Exception;
	
	public List<Movie> findMovieByName(String name);

	public List<Movie> findMovieByActorName(String actorName);

	public List<Movie> findMovieByNameAndActorName(String name, String actorName);

	public Movie getMovieById(Long id) throws Exception;



}
