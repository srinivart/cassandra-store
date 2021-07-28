package com.srinivart.util;

import com.datastax.driver.core.*;
import com.datastax.oss.driver.shaded.guava.common.collect.Maps;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srinivart.model.Employee;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class Helper {

    Session session;

    public static final String INSERT_INTO_TABLE1 = "INSERT INTO employee (empid, name, designation, salary)" +
            "VALUES(?, ?, ?, ?);";

    public static final String UPDATE_QUERY = "UPDATE employee SET designation=?,salary=? WHERE empid=?";

    public Helper() {
        this.session = getSession();
    }

    public void createTable(){
        String query = "CREATE TABLE employee(empid int PRIMARY KEY, "
                + "name text, "
                + "designation text, "
                + "salary int);";
        session.execute(query);
        System.out.println("Table created");
    }

    public void insertIntoTable(){
        String query1 = "INSERT INTO employee (empid, name, designation, salary)"
                + " VALUES(1,'teju', 'Developer', 65000);" ;
        session.execute(query1);
        System.out.println("Inserted into TAble");
    }

    public void readFromTable(){
        String query = "SELECT * FROM employee";
        session.execute(query);
        ResultSet result = session.execute(query);
        System.out.println(result.all());

    }

    public void updateTable(){
        String query = " UPDATE employee SET name='srini',salary=85000 WHERE empid=1" ;
        session.execute(query);
        System.out.println("updated table");
    }

    public void deleteFromTable(){
        String query = "DELETE FROM employee WHERE empid=1;";
        session.execute(query);
        System.out.println("Data deleted");
    }



    public void dropTable(){
        String query = "DROP TABLE employee;";
        session.execute(query);
        System.out.println("Table dropped");
    }



    public Session getSession(){
        //Creating Cluster object
        Cluster cluster = Cluster.builder()
                .withoutJMXReporting()
                .addContactPoint("127.0.0.1").build();
        //Creating Session object
        Session session = cluster.connect("srinivart");
        return session;
    }


    public void createKeySpace(){
        String query = "CREATE KEYSPACE cassandra_store WITH replication "
                + "= {'class':'SimpleStrategy', 'replication_factor':1};";

        Cluster cluster = Cluster.builder()
                .withoutJMXReporting()
                .addContactPoint("127.0.0.1").build();

        Session session = cluster.connect();

        session.execute(query);

        session.execute("USE cassandra_store");
        System.out.println("Keyspace created");
    }

    public void dropKeyspace(){
        String query = "Drop KEYSPACE cassandra_store";
        Cluster cluster = Cluster.builder()
                .withoutJMXReporting()
                .addContactPoint("127.0.0.1").build();

        Session session = cluster.connect();

        session.execute(query);
        System.out.println("Keyspace deleted");
    }


    public void insertIntoTable1(int empid,String name,String designation, int salary){
        System.out.println("insert1");
        //String query1 = INSERT_INTO_TABLE1+ empid+","+name+","+designation+","+salary;   // this is not working
        //String query1 = "INSERT INTO employee (empid, name, designation, salary)"+ " VALUES(1,'teju', 'Developer', 65000);" ;
        //session.execute(query1);


        PreparedStatement prepared = session.prepare(INSERT_INTO_TABLE1);
        BoundStatement bound = prepared.bind(empid,name,designation,salary);
        session.execute(bound);
        System.out.println("Inserted into TAble");
    }


    public Employee readFromTable1(int empId) {
        System.out.println("reading from TAble");
        String query = "SELECT * FROM employee WHERE empId ="+empId+";";
        session.execute(query);
        ResultSet resultSet = session.execute(query);
       // System.out.println(resultSet.all());

//        for(Row row: resultSet){
//            System.out.println(row.getString(1));
//        }
//        Row row = resultSet.one();
//        System.out.println(" --> : " +row.getString(0));
        //return Employee.parse(row.getString(0));


        Employee employee = getObject(resultSet);
        return employee;

    }

    public Employee getObject(ResultSet resultSet){
        Employee employee = null;
        Map<String, String> columns = queryColumns(resultSet);
        List<JSONObject> result = new ArrayList<>();
        for (Row row : resultSet) {
            JSONObject json = new JSONObject();
            columns.forEach((k, v) -> {
                if (columns.get(k).contains("timestamp")) {
                    if (row.getObject(k) != null) {
                        try {
                            json.put(k, ((Date) row.getObject(k)).getTime());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        json.put(k, row.getObject(k));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            result.add(json);
            System.out.println("employe Json data: -> " + json);


            ObjectMapper mapper = new ObjectMapper();
            byte[] jsonData = json.toString().getBytes();

            try {
                employee = mapper.readValue(jsonData, Employee.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("name ->: "+ employee.getName());

        }
        return employee;
    }

    public static Map<String, String> queryColumns(ResultSet rs) {
        try {
            ColumnDefinitions rscd = rs.getColumnDefinitions();
            int count = rscd.size();
            HashMap<String, String> reflect = Maps.newHashMap();
            for (int i = 0; i < count; i++) {
                String column = rscd.getName(i);
                String type = rscd.getType(i).getName().name().toLowerCase();
                reflect.put(column, type);
            }
            return reflect;
        } catch (Exception e) {
        }
        return Maps.newHashMap();
    }



    public Employee updateTable1(int empid,Employee employee){
        System.out.println("Update TAble");
        //String query = " UPDATE employee SET designation='senior developer',salary=85000 WHERE empid=1" ;
        //String query =  "UPDATE employee SET (name,salary) VALUES(?, ?) WHERE empid="+empid+";";


        //session.execute(query);
        PreparedStatement prepared = session.prepare(UPDATE_QUERY);
        BoundStatement bound = prepared.bind(employee.getDesignation(),employee.getSalary(),empid);
        //session.execute(bound);

        ResultSet resultSet = session.execute(bound);

        System.out.println("updated table");

        Employee employee1 = getObject(resultSet);
        //System.out.println("employee -> "+employee1.toString());
        return employee1;
    }

}
