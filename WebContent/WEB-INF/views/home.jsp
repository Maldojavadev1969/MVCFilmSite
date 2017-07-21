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
		Id: 
		<input type="text" name="filmId"> <input type="submit"
			value="look  up film">
	</form>
	<form action="getTitle.do" method="get">
		Keyword: 
		<input type="text" name="keyword"> <input type="submit"
			value="look  up film">
	</form>
	<c:choose>
		<c:when test="${film != null}">
			<h4>${film}</h4>
			<ul>
				<c:forEach var="actor" items="${film.cast}">
					<li>${actor}</li>
				</c:forEach>
			</ul>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${films != null}">
			<c:forEach var="film" items="${films}">
				<h4>${film}</h4>
				<ul>
					<c:forEach var="actor" items="${film.cast}">
						<li>${actor}</li>
					</c:forEach>
				</ul>
			</c:forEach>
		</c:when>
	</c:choose>

</body>
</html>