package com.skilldistillery.film.data;

import java.util.List;

public class Film{
	
	//using camel case for fields.. snake case from database
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	
	private List<Actor> cast;

	public List<Actor> getCast() {
		return cast;
	}

	public void setCast(List<Actor> cast) {
		this.cast = cast;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int release_year) {
		this.releaseYear = release_year;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int language_id) {
		this.languageId = language_id;
	}
	
	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rental_duration) {
		this.rentalDuration = rental_duration;
	}
	
	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rental_rate) {
		this.rentalRate = rental_rate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacement_cost) {
		this.replacementCost = replacement_cost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String special_features) {
		this.specialFeatures = special_features;
	}
	
	@Override
	public String toString() {
		return id + ": " + title + " (" + releaseYear + ") - " + rating;
	}

	//need to find out if i can add this to the constructor  List<Actor> cast .. i was using this for the actor section
	public Film(int id, String title, String description, int releaseYear, int languageId, int rentalDuration, double rentalRate, int length, double replacementCost, String rating, String specialFeatures) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		//this.cast = cast;

	}	
	//
	public Film() {
		// i dont have to have a super()
		// because Spring will provide one for me if its not here
		// in the no arg constructor
		super();
	}
	
	
}
