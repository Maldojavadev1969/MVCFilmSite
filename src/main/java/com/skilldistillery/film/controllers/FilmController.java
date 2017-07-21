package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.Film;
import com.skilldistillery.film.data.FilmDao;

@Controller
public class FilmController {

	@Autowired
	private FilmDao dao;
	
	@RequestMapping(path = "home.do")
	public ModelAndView home() {
	  return new ModelAndView("WEB-INF/views/home.jsp");
	}
	
	@RequestMapping(path = "getTitle.do", params = "filmId")
	public ModelAndView getFilmTitleById(@RequestParam(name = "filmId") Integer filmId) {
	  String viewName = "WEB-INF/views/home.jsp";
	  ModelAndView mv = new ModelAndView(viewName);
	  Film film = dao.getFilmTitleById(filmId);
	  mv.addObject("film", film);
	  return mv;
	}

	@RequestMapping(path = "getTitle.do", params = "keyword")
	public ModelAndView getFilmTitlesByKeyWord(@RequestParam(name = "keyword") String keyword) {
	  String viewName = "WEB-INF/views/home.jsp";
	  ModelAndView mv = new ModelAndView(viewName);
	  List<Film> films = dao.getFilmTitlesByKeyWord(keyword);
	  mv.addObject("films", films);
	  return mv;
	}
}
