package com.skilldistillery.film.data;

import java.util.List;

public interface FilmDao {
	Film getFilmTitleById(int id);
	List<Film> getFilmTitlesByKeyWord(String keyword);
	List<Actor> getActorsByFilm(Film film);
	
}
