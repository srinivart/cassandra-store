package com.srinivart.util;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

public class Helper {

    Session session;

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


}
