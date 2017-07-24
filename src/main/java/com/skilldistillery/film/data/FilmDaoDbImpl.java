package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

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
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features  FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				// get film columns from DB result set and load into Film object constructor
				film = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10),
						rs.getString(11));
				// now pass that film object to the getActors method and set the
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

	// ************************************** test area for adding a film
	public Film addFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			// int id, String title, String description, int releaseYear, int languageId,
			// int rentalDuration, double rentalRate, int length, double replacementCost,
			// String rating, String specialFeatures
			String sql = "Insert Into Film(title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			int updateCount = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				film.setId(rs.getInt(1));
			}
			
			//may need to comment this out again
//			 if (updateCount == 1) {
//			 ResultSet keys = stmt.getGeneratedKeys();
//			 if (keys.next()) {
//			 int newActorId = keys.getInt(1);
//			 film.setId(newActorId);
//			 if (film.getFilms() != null && actor.getFilms().size() > 0) {
//			 sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
//			 stmt = conn.prepareStatement(sql);
//			 for (Film film : actor.getFilms()) {
//			 stmt.setInt(1, film.getId());
//			 stmt.setInt(2, newActorId);
//			 updateCount = stmt.executeUpdate();
//			 }
//			 }
//			 }
//			 } else {
//			 actor = null;
//			 }
			 // end of possible comment out section
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + film);
		}
		return film;
	}

	public Film updateFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			// SET column1 = value1, column2 = value2, ...WHERE condition;

			String sql = "Update Film Set title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ? where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			stmt.setInt(11, film.getId());

			//stmt.execute();
			int updateCount = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			//need this to display to user to show what item was updated
//			if (rs.next()) {
//				film.setId(rs.getInt(1));
//			}

			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			//throw new RuntimeException("Error inserting actor " + film);
		}
		return film;
	}

	public int deleteFilm(int id) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql4 = "select id from inventory_item where film_id = ?";
			PreparedStatement stmt4 = conn.prepareStatement(sql4, Statement.RETURN_GENERATED_KEYS);
			stmt4.setInt(1, id);
			stmt4.executeQuery();// if deleting need this statement not execute.query
			ResultSet keys = stmt4.executeQuery();
			while (keys.next()) {
				System.out.println("in first while");
				String sql5 = "select id from rental where inventory_id = ?";
				PreparedStatement stmt5 = conn.prepareStatement(sql5, Statement.RETURN_GENERATED_KEYS);
				stmt5.setInt(1, keys.getInt(1));
				stmt5.executeQuery();// if deleting need this statement not execute.query
				ResultSet keys2 = stmt5.getGeneratedKeys();
				while (keys2.next()) {
					String sql6 = "Delete from payment where rental_id = ?";
					PreparedStatement stmt6 = conn.prepareStatement(sql6, Statement.RETURN_GENERATED_KEYS);
					stmt6.setInt(1, keys2.getInt(1));
					stmt6.execute();
				}
				keys2.close();
				String sql7 = "delete from rental where inventory_id = ?";
				System.out.println(sql7 + " " + keys.getInt(1));
				PreparedStatement stmt7 = conn.prepareStatement(sql7, Statement.RETURN_GENERATED_KEYS);
				stmt7.setInt(1, keys.getInt(1));
				stmt7.execute();// if deleting need this statement not execute.query
			}
			keys.close();
			
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			String sql3 = "Delete From inventory_item WHERE  film_id = ?";
			PreparedStatement stmt3 = conn.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
			stmt3.setInt(1, id);
			stmt3.execute();// if deleting need this statement not execute.query
			String sql8 = "Delete From film_category WHERE  film_id = ?";
			PreparedStatement stmt8 = conn.prepareStatement(sql8, Statement.RETURN_GENERATED_KEYS);
			stmt8.setInt(1, id);
			stmt8.execute();// if deleting need this statement not execute.query
			String sql2 = "Delete From Film_actor WHERE  film_id = ?";
			PreparedStatement stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			stmt2.setInt(1, id);
			stmt2.execute();// if deleting need this statement not execute.query
			String sql = "Delete From Film WHERE  id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			stmt.execute();// if deleting need this statement not execute.query

			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error could not delete film with id " + id);
		}
		return id;
	}

	// ************************************** test area for adding a film to the
	// filmdaoimpl

	@Override
	public List<Film> getFilmTitlesByKeyWord(String keyword) {
		List<Film> films = new ArrayList<>();
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			// Querry
			// String sql = "SELECT id, title, description, release_year, language_id,
			// length, rating from film where description like ? Or title like ? ";
			String sql = "SELECT id, title, language_id, rental_duration, rental_rate, replacement_cost rating from film where description like ? Or title  like ? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			// Results
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10),
						rs.getString(11));

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

	// get the actor for that film and add to the array list until the calling
	// method loop is done
	@Override
	public List<Actor> getActorsByFilm(Film film) {
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
				actor = new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
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
