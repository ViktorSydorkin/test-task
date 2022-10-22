package com.testtask.repository.util;

public interface SQL {
    /*QUERIES FOR EMPLOYEE*/
    String GET_ALL_EMPLOYEES = "SELECT * FROM employees JOIN departments ON department = department_id ORDER BY employee_id LIMIT :limit OFFSET :offset;";
    String GET_EMPLOYEE_BY_ID = "SELECT * FROM employees JOIN departments ON department = department_id WHERE employee_id=:id;";
    String GET_EMPLOYEE_BY_NAME = "SELECT * FROM employees JOIN departments ON department = department_id WHERE name LIKE :name;";
    String ADD_EMPLOYEE = "INSERT INTO employees (name, active, department) VALUES (:name, :active, :department);";
    String UPDATE_EMPLOYEE = "UPDATE employees SET  name = :name, active=:active, department=:department WHERE employee_id=:id;";
    String DELETE_EMPLOYEE = "DELETE FROM employees WHERE employee_id=:id;";
    /*QUERIES FOR DEPARTMENT*/
    String GET_ALL_DEPARTMENTS = "SELECT * FROM departments;";
    String GET_DEPARTMENT_BY_ID = "SELECT * FROM departments WHERE department_id =:id;";
    String ADD_DEPARTMENT = "INSERT INTO departments (department_name) VALUES (:name);";
    String UPDATE_DEPARTMENT = "UPDATE departments SET department_name =:name WHERE department_id =:id;";
    String DELETE_DEPARTMENT = "DELETE FROM departments WHERE department_id =:id;";
    /*QUERIES FOR USER*/
    String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email =:email;";
    String ADD_USER = "INSERT INTO users (username, email, password, enabled) VALUES (:username, :email, :password, :enabled);";
}
