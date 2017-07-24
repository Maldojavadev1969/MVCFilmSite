package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

//	@RequestMapping(path = "getTitle.do", params = "keyword")
//	public ModelAndView getFilmTitlesByKeyWord(@RequestParam(name = "keyword") String keyword) {
//	  String viewName = "WEB-INF/views/home.jsp";
//	  ModelAndView mv = new ModelAndView(viewName);
//	  List<Film> films = dao.getFilmTitlesByKeyWord(keyword);
//	  mv.addObject("films", films);
//	  return mv;
//	}
	
	@RequestMapping(path="addFilm.do",
			method=RequestMethod.POST)
	public ModelAndView newFilm(Film film) {
		// why is this breaking ??? because i forgot about line 19 "dao"
		dao.addFilm(film);
		ModelAndView mv = new ModelAndView();
		//dont forget to add proper path like below
		mv.setViewName("WEB-INF/views/home.jsp");
		mv.addObject(film);
		return mv;
	}
	
	@RequestMapping(path = "deleteFilm.do")
	public ModelAndView deleteFilm(@RequestParam(name = "id") int id) {
	  String viewName = "WEB-INF/views/home.jsp";
	  ModelAndView mv = new ModelAndView(viewName);
	  dao.deleteFilm(id);
	  mv.setViewName("WEB-INF/views/home.jsp");
	  mv.addObject("message", "delteted film id" + id);
	  return mv;
	}
	//	get film by id and send it back to the home page
	//  path to  the action on the jsp
	@RequestMapping(path = "editForm.do")
	public ModelAndView updateFilm(@RequestParam(name = "filmId") int id) {
		String viewName = "WEB-INF/views/home.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		//get film method from dao
		mv.setViewName("WEB-INF/views/home.jsp");
		// call method and get id and then pass it to home page!!
		mv.addObject("editfilm", dao.getFilmTitleById(id));
		return mv;
	}
	
	// need to update film
	@RequestMapping(path = "updateFilm.do", method=RequestMethod.POST)
	public ModelAndView updateFilm(Film film) {
		String viewName = "WEB-INF/views/home.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		// call method and get id and then pass it to home page!!
		dao.updateFilm(film);
		return mv;
	}
	
	
	
}
