Feature: Employee CRUD Operation

  Scenario: create a employee record based
  Given Enter the employee record
  When employee record save into the database
  Then validate the employee record.


  Scenario: Get an employee record
    Given The user gives an employee id
    When  Employee record is present based on that id
    Then It will return the Employee record based on that id

  Scenario: get the list of employee record
    Given there is a get employee request not based on ID
    Then return list of all employees from the database

  Scenario: delete the employee record
    Given Employees exist in the database with given id
    Then the employee information should be deleted
