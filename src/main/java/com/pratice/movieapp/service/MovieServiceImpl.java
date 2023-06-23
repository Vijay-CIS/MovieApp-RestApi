package com.pratice.movieapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratice.movieapp.exceptions.MovieNotFoundException;
import com.pratice.movieapp.model.Movie;
import com.pratice.movieapp.repository.MovieRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Override
	public Movie insertMovie(Movie movie) throws Exception {
		List<Movie> movies = movieRepository.findByName(movie.getName());
		System.out.println("Movie Size" + movies.size());
		if (movies.size() > 0) {
			throw new Exception("Movie Name Already Exist");
		}
		return movieRepository.save(movie);

	}

	@Override
	public List<Movie> getAllMovies() {

		return movieRepository.findAll();
	}

	@Override
	public List<Movie> findMovieByName(String name) {
		return movieRepository.findByName(name);

	}

	@Override
	public void deleteMovie(Long id) {

		movieRepository.deleteById(id);

	}

	@Override
	public Movie updateMovie(Movie m) throws Exception {
		Movie movie = movieRepository.findById(m.getId()).orElse(null);
		if (movie != null) {
			movie.setName(movie.getName());
			movie.setActorName(movie.getActorName());
			return movieRepository.save(m);
		} else {
			throw new Exception("Invalid Movie Id :" + m.getId());
		}
	}

	@Override
	public List<Movie> findMovieByActorName(String actorName) {

		return movieRepository.findByActorName(actorName);
	}

	@Override
	public List<Movie> findMovieByNameAndActorName(String name, String actorName) {

		// return movieRepository.findByNameAndActorName(name,actorName);
		return movieRepository.search1(name, actorName);
	}

	@Override
	public Movie getMovieById(Long id) throws Exception {

		Movie movie = movieRepository.findById(id).orElse(null);
		if (movie == null) {
			// throw new Exception("Invalid Movie Id" + id);
			throw new MovieNotFoundException("Movie Not Found :" + id);
		}
		return movie;
	}

}
