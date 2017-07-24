<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MMDB</title>
</head>
<body>
	<form action="getTitle.do" method="get">
		Id: <input type="text" name="filmId"> <input type="submit"
			value="look  up film">
	</form>
	<!-- <form action="getTitle.do" method="get">
		Keyword: <input type="text" name="keyword"> <input
			type="submit" value="look  up film">
	</form> --> 
	<c:if test="${editfilm == null }">
	<form action="addFilm.do" method="post">
		<hr>
		Add a film
		<hr>
		</br> <input type="text" name="title"> title:</br> <input type="text"
			name="description"> description:</br> <select name="releaseYear">
			<%
				for (int i = 1900; i < 2017; i++) {
			%>
			<option><%=i%> </option>
			<%
				}
			%>
		</select> release year:</br> <input type="text" name="languageId"> language
		id:</br> <input type="text" name="rentalDuration"> rental duration:</br>
		<input type="text" name="rentalRate"> rental rate:</br> <input
			type="text" name="length"> length:</br> <input type="text"
			name="replacementCost"> replacement cost:</br> <select
			name="rating">
			<option>G</option>
			<option>PG</option>
			<option>PG13</option>
			<option>NC17</option>
		</select> rating:</br> <select name="specialFeatures">
			<option>Trailers</option>
			<option>Commentaries</option>
			<option>Deleted Scenes</option>
			<option>Behind The Scenes</option>
		</select> special features:</br> <input type="submit" value="add film">
	</form>
	</c:if>
	<c:if test="${editfilm != null }">
	<form action="updateFilm.do" method="post">
		<hr>
		Edit a film
		<hr>
		</br> 
		<input type="hidden" name="id" value="${editfilm.id}"> 
		<input type="text" name="title" value="${editfilm.title}"> title:</br> 
		<input type="text" name="description" value="${editfilm.description}"> description:</br>
		
			<%
				for (int i = 1900; i < 2017; i++) {
			%>
			<option><%=i%> </option>
			<%
				}
			%>
		</select> release year:</br> <input type="text" name="languageId" value="${editfilm.languageId}"> language
		id:</br> 
		<input type="text" name="rentalDuration" value="${editfilm.rentalDuration}"> rental duration:</br>
		<input type="text" name="rentalRate" value="${editfilm.rentalRate}"> rental rate:</br> 
		<input type="text" name="length" value="${editfilm.length}"> length:</br> 
		<input type="text" name="replacementCost" value="${editfilm.replacementCost}"> replacement cost:</br> 
		<select
			name="rating">
			<option>G</option>
			<option>PG</option>
			<option>PG13</option>
			<option>NC17</option>
		</select> rating:</br> <select name="specialFeatures">
			<option>Trailers</option>
			<option>Commentaries</option>
			<option>Deleted Scenes</option>
			<option>Behind The Scenes</option>
		</select> special features:</br> <input type="submit" value="Edit Film">
	</form>
	</c:if>
	<hr>
	Delete Film:
	<form action="deleteFilm.do" method="post">
		<input type="text" name="id">film id:</br> <input type="submit"
			value="delete film"> ${message}
	</form>
	<c:choose>
		<c:when test="${film != null}">
			<h4>${film}</h4>
			<form action="editForm.do" method="get">
				<ul>
					<c:forEach var="actor" items="${film.cast}">
						<li>${actor}</li>
					</c:forEach>
				</ul>
				<input type="hidden" name="filmId" value="${film.id }" /> <input
					type="submit" value="Edit" />
			</form>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${films != null}">
			<c:forEach var="film" items="${films}">
				<h4>${film}</h4>
				<form action="editForm.do" method="get">
					<ul>
						<c:forEach var="actor" items="${film.cast}">
							<li>${actor}</li>
						</c:forEach>
					</ul>
					<input type="hidden" name="filmId" value="${film.id }" /> <input
						type="submit" value="Edit" />
				</form>
			</c:forEach>
		</c:when>
	</c:choose>

</body>
</html>