Implement SPA with authentication page and one more page + RestAPI on backend for CRUD operations on one table.
DB:
	tblEmployees:
		empID (identity field)
		empName 
		empActive
		emp_dpID – foreign key to tblDepartments
	tblDepartments:
		dpID
		dpName

It should have one “Search” feature. And it should search ONLY with “Start with” criteria! (not include!).
Conditions: 
1.	Table theoretically can have more fields and a lot of data.
2.	So, paging is essential part of this test task.
3.	Please use MySQL for test task.
4.	Don’t use Hibernate or any other ORM.
5.	Please configure swagger.
6.	Please write a small tutorial how to start a project and attach a database dump with test data.

What was done:
1. Created a DB, the creation script is in "test-task-script.sql".
2. A spring boot 3-layer (controller, service, repository) based app with the NamedQueryJdbcOperation as a connection to the DB.
3. MySQL as a DB.
4. Exception handling for a controllers.
5. Swagger was added to the project and configured.
6. A Sl4j logger was added and configured a base logback realization.
7. Test was written and the coverage is 83%