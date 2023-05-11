package com.pratice.movieapp;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;

import com.pratice.movieapp.exceptions.MovieNotFoundException;
import com.pratice.movieapp.model.Movie;
import com.pratice.movieapp.repository.MovieRepository;
import com.pratice.movieapp.service.MovieService;
import com.pratice.movieapp.service.MovieServiceImpl;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {
	
     @Mock
     private MovieRepository movieRepository;
     
	@InjectMocks
	private MovieServiceImpl movieService;
	
	@Before
	public void setUp() {
			
		 MockitoAnnotations.initMocks(this);
	}
	
	Movie mockMovie = new Movie(1l,"Gilli","Vijay");
	
	List<Movie> mockMovieList = List.of(mockMovie);
	
	@Test
	public void findAllMovies() {
		Mockito.when(movieRepository.findByName((String) any(String.class))).thenReturn(mockMovieList);
		List<Movie> findMovieByName = movieService.findMovieByName("Thozha6");
		System.out.println("Size :"+ findMovieByName.size());
		assert(findMovieByName.size()>0);
		
	}
	@Test
	@DisplayName("Get Single Movie By Id Method")
	public void getMovieById() throws Exception {
		Mockito.when(movieRepository.findById((Long) any(Long.class))).thenReturn(Optional.of(mockMovie));
		Long id = 2L;
		Movie newMovie = movieService.getMovieById(id);
		assertNotNull(newMovie);
	}
	
	@Test
	@DisplayName("Get Single Movie By InvalidId Method")
	public void getMovieByInvalidId() throws Exception {
		Mockito.when(movieRepository.findById((Long) any(Long.class))).thenReturn(Optional.ofNullable(null));
		Long id = 2L;
		MovieNotFoundException thrown = Assertions
				.assertThrows(MovieNotFoundException.class, () -> {
					Movie newMovie = movieService.getMovieById(id);
				}, "MovieNotFoundException error was expected");
	
	Assertions.assertEquals("Invalid Movie Id"+ id, thrown.getMessage());
		
		//assertNotNull(newMovie);
	}
	


}
