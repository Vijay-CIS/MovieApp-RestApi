package com.pratice.movieapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pratice.movieapp.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByName(String name);

	List<Movie> findByActorName(String actorName);

//List<Movie> findByNameAndActorName(String name, String actorName);

// @Query("from Movie m where name=:name and actorName=:actorName")
// List<Movie> search(@Param("name") String name, @Param("actorName") String actorName);

	@Query("from Movie m where name=?1 and actorName=?2")
	List<Movie> search1(String name, String actorName);

	

}
