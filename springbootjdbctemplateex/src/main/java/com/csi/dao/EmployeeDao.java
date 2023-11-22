package com.csi.dao;

import com.csi.model.Employee;

import java.util.List;

public interface EmployeeDao {

    public void signUp(Employee employee);

    public boolean signIn(String empEmailId, String empPassword);

    public List<Employee> findAll();

    public void updateData(int empId, Employee employee);

    public void deleteById(int empId);
}
