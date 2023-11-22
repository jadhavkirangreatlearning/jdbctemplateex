package com.csi.dao;

import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    String INSERT_SQL = "insert into employee(empid, empname, empaddress, empsalary, empcontactnumber, empemailid, emppassword)values(?, ?, ?, ?, ?, ?, ?)";

    String SELECT_ALL_SQL = "select * from employee";

    String UPDATE_SQL = "update employee set empname=?, empaddress=?, empsalary=?, empcontactnumber=?, empemailid=?, emppassword=? where empid=?";

    String DELETE_SQL = "delete from employee where empid=?";

    private Employee employee(ResultSet resultSet, int i) throws SQLException {
        return Employee.builder().empId(resultSet.getInt(1)).empName(resultSet.getString(2)).empAddress(resultSet.getString(3)).empSalary(resultSet.getDouble(4)).empContactNumber(resultSet.getLong(5)).empEmailId(resultSet.getString(6)).empPassword(resultSet.getString(7)).build();
    }

    @Override
    public void signUp(Employee employee) {
        jdbcTemplate.update(INSERT_SQL, employee.getEmpId(), employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(), employee.getEmpEmailId(), employee.getEmpPassword());
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean flag = false;

        for (Employee employee : findAll()) {
            if (employee.getEmpEmailId().equals(empEmailId)
                    && employee.getEmpPassword().equals(empPassword)) {
                flag = true;
            }
        }

        return flag;
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, this::employee);
    }

    @Override
    public void updateData(int empId, Employee employee) {
        jdbcTemplate.update(UPDATE_SQL, employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(), employee.getEmpEmailId(), employee.getEmpPassword(), empId);
    }

    @Override
    public void deleteById(int empId) {
        jdbcTemplate.update(DELETE_SQL, empId);
    }
}
