# MVCFilmSite
Author: Michael Maldonado SD11
This project was a Spring STS MVC that persisted
data to a MySql database where you could
add(create)/read/update/delete movies.

technologies used:
Spring MVC
Java Object Oriented programming
SQL commands to perform CRUD operations
gradle
MySql database
html

I would like to add the ability to
perform CRUD operations with the actors table.
with time permitting I would like to add
bootstrap to make the modernize the site.

The stumbling points that i ran into on this
project was trying to delete an existing Film
in the database because it was simpler to delete a film that was newly added without actors. In order to delete films where there were foreign keys associated with the film tables those associations needed to be deleted as well prior to the film being deleted.
