package cucumber;

import com.example.demo.controller.EmpController;
import com.example.demo.dao.EmpDao;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

public class MyStepdefs {
    @Autowired
    private EmpController controller;
    @Autowired
    private EmpService service;
    @Autowired
    private EmpDao dao;

    Employee employee = new Employee();

    @Given("^Enter the employee record$")
    public void enterTheEmployeeRecord() {
        employee.setFirstName("Iron");
        employee.setLastName("Man");
        employee.setEmailId("iron@man.email");
        // System.out.println(employee);
        this.controller.addEmp(employee);
    }

    @When("^employee record save into the database$")
    public void employeeRecordSaveIntoTheDatabase() {
        Assertions.assertNotNull(employee);
        //   System.out.println("when:- "+ employee);
        this.service.addEmployee(employee);
        this.dao.save(employee);
    }

    @Then("validate the employee record.")
    public void validateTheEmployeeRecord() {
        Assertions.assertEquals(employee.getFirstName(), this.dao.save(employee).getFirstName());
    }

    @Given("The user gives an employee id")
    public void theUserGivesAnEmployeeId() {
        this.controller.getOneEmp("1");
    }

    @When("Employee record is present based on that id")
    public void employeeRecordIsPresentBasedOnThatId() {
        this.service.getEmployee(1);
    }

    @Then("It will return the Employee record based on that id")
    public Employee itWillReturnTheEmployeeRecordBasedOnThatId() {
        Employee emp = this.dao.getById(1);
        return emp;
    }

    @Given("there is a get employee request not based on ID")
    public void thereIsAGetEmployeeRequestNotBasedOnID() {
        this.controller.getEmp();
        this.service.getEmployee();
    }

    @Then("return list of all employees from the database")
    public List<Employee> returnListOfAllEmployeesFromTheDatabase() {
        return this.dao.findAll();
    }

    @Given("Employees exist in the database with given id")
    public void employeesExistInTheDatabaseWithGivenId() {
        this.controller.getOneEmp("14");
    }

    @Then("the employee information should be deleted")
    public void theEmployeeInformationShouldBeDeleted() {
        this.dao.deleteById(14);
    }
}
