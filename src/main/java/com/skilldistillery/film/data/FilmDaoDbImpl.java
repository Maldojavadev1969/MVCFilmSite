package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilmDaoDbImpl implements FilmDao {
	private static String url = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";

	public FilmDaoDbImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	@Override
	public Film getFilmTitleById(int id) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, title, description, release_year, language_id, length, rating  FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				//get film columns from DB result set and load into Film object constructor
				 film = new Film( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
						rs.getString(7));
				 //now pass that film object to the getActors method and set the
				 // film cast in the film object on return from the method call
				 film.setCast(getActorsByFilm(film));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}


	@Override
	public List<Film>  getFilmTitlesByKeyWord(String keyword) {
		List<Film> films = new ArrayList<>();
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			// Querry
			String sql = "SELECT id, title, description, release_year, language_id, length, rating from film where description like ? Or title  like ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"  + keyword + "%");
			stmt.setString(2, "%"  + keyword + "%");
			// Results
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
						rs.getString(7));
				
				film.setCast(getActorsByFilm(film));
				
				
			
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}
	
	//get the actor for that film and add to the array list until the calling method loop is done
	@Override
	public List<Actor>  getActorsByFilm(Film film) {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			// Querry
			String sql = "SELECT id, first_name, last_name from actor where id in (select actor_id from film_actor where film_id = ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			// Results
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				actor = new Actor( rs.getInt(1), rs.getString(2), rs.getString(3));
				actors.add(actor);
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

}
