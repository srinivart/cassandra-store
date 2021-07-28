package com.srinivart.constants;

public interface EmployeeConstants {

    public static final String GET_ALL_EMPLOYEES = "/employees";

    public static final String FIND_EMPLOYEE_BY_ID = "/employees/find/{id}";

    public static final String ADD_NEW_EMPLOYEE = "/employees/add";

    public static final String UPDATE_EMPLOYEE_BY_ID = "/employees/update/{id}";

    public static final String DELETE_EMPLOYEE_BY_ID = "/employees/delete/{id}";


    public static final String INSERT_INTO_TABLE = "INSERT INTO employee WHERE id = ? AND name = ?" +
            "designation= ? AND salary =?;";

    public static final String INSERT_INTO_TABLE1 = "INSERT INTO employee (empid, name, designation, salary)" +
            "VALUES(?, ?, ?, ?);";
}
