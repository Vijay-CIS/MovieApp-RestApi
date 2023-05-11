package com.pratice.movieapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pratice.movieapp.model.Movie;
import com.pratice.movieapp.repository.MovieRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieRepositoryTest {

	@Autowired
	MovieRepository movieRepository;
	

	@Test
	@DisplayName("Save Method")
	public void save() {
		try {
		Movie m = new Movie();
		m.setName("Valimai");
		m.setActorName("Ajith");
		Movie newMovie = movieRepository.save(m);
		assertNotNull(newMovie);
		assertNotNull(newMovie.getId());
		}
		catch(Exception e){
		 fail("Error Occurs While Saving Movie:"+ e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Save Movie Without Actor Name")
	public void save1() {
		try {
		Movie m = new Movie();
		m.setName("Valimai");
	//	m.setActorName("Ajith");
		Movie newMovie = movieRepository.save(m);
		 fail("Error Occurs While Saving Movie without actor name");
		
		}
		catch(Exception e){
			assert(true);
			
		}
	}
	
	@Test
	@DisplayName("Save Method with Same Name")
	public void save2() {
		try {
		Movie m1 = new Movie();
		m1.setName("Thozha6");
		m1.setActorName("Karthi");
		Movie m2 = new Movie();
		m2.setName("Thozha6");
		m2.setActorName("Karthi");
		

		Movie newMovie1 = movieRepository.save(m1);
		Movie newMovie2 = movieRepository.save(m2);
		fail("Error Occur same movie must be saved twice");
		}catch(Exception e) {
			assert(true);
			
		}
		
	}



	@Test
	@DisplayName("List of Movie Method")
	public void getAllMovies() {

		List<Movie> list = movieRepository.findAll();
		List<Movie> mlist = new ArrayList<>();
		for (Movie m : list)
			mlist.add(m);
		assertThat(mlist.size()>2);

	}
	
	@Test
	@DisplayName("Get Single Movie By Id Method")
	public void getMovieById() {
	
		Long id = 2L;
		Optional<Movie> newMovie = movieRepository.findById(id);
		if (newMovie.isPresent()) {
			assert(true);
		}else {
			assert(false);
		}
		
	}
	
	@Test
	@DisplayName("Get Single Movie By Invalid Id Method")
	public void getMovieByInvalidId() {

		Long id = 200L;
		Optional<Movie> newMovie = movieRepository.findById(id);
		if (newMovie.isPresent()) {
			assert(false);
		}else {
			assert(true);
		}
		
	}
	
	@Test
	@DisplayName("Get List Movie By valid ActorName Method")
	public void getMovieByActorName() {
		
		List<Movie> newMovieList = movieRepository.findByActorName("Ajith");
		assertNotNull(newMovieList);
		assertThat(newMovieList.size()>0);
		
		
	}
	@Test
	@DisplayName("Get List Movie By Invalid ActorName Method")
	public void getMovieByInvalidActorName() {
		
		List<Movie> newMovieList = movieRepository.findByActorName("Surya123");
		assertNotNull(newMovieList);
		assertThat(newMovieList.size()==0);
		
		
	}
	@Test
	@DisplayName("Get List Movie By Null ActorName Method")
	public void getMovieByNullActorName() {
		String actorName = null;
		List<Movie> newMovieList = movieRepository.findByActorName(actorName);
		assertNotNull(newMovieList);
		assertThat(newMovieList.size()==0);
		
		
	}
	
	
	@Test
	@DisplayName("Get List Movie By Valid MovieName Method")
	public void getMovieByName() {
		List<Movie> list = movieRepository.findByName("Thozha");
		assertNotNull(list);
		assertThat(list.size()>0);
		
	}
	@Test
	@DisplayName("Get List Movie By InValid MovieName Method")
	public void getMovieByInvalidName() {
		List<Movie> list = movieRepository.findByName("Thozha");
		assertNotNull(list);
		assertThat(list.size()==0);
		
	}
	
	@Test
	@DisplayName("Get List Movie By Null MovieName Method")
	public void getMovieByNullMovieName() {
		String movieName = null;
		List<Movie> newMovieList = movieRepository.findByActorName(movieName);
		assertNotNull(newMovieList);
		assertThat(newMovieList.size()==0);
		
		
	}
	


	@Test
	@DisplayName("Update Movie Method")
	public void updateMovie() {
        Long id = 2L;
		Movie existingMovie = movieRepository.findById(id).get();
		existingMovie.setActorName("Kamal");
		Movie updatedMovie = movieRepository.save(existingMovie);
		assertEquals("Kamal",updatedMovie.getActorName());
		
	}
	
	
	@Test
	@DisplayName("Delete Movie Method")
	public void deleteMovie() {

		Long id = 3L;
		movieRepository.deleteById(id);
		
		Optional<Movie> existingMovie = movieRepository.findById(id);
		if (existingMovie.isPresent()) {
			assert(false);
		}else {
			assert(true);
		}
		
	}
	
	
}
