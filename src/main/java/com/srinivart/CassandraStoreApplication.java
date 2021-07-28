package com.srinivart;

import com.srinivart.util.Helper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@SpringBootApplication
public class CassandraStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraStoreApplication.class, args);


		Helper helper = new Helper();

		helper.createTable();

		helper.insertIntoTable();

		helper.readFromTable();

        helper.updateTable();

		helper.deleteFromTable();


		helper.dropTable();



		helper.createKeySpace();

		helper.dropKeyspace();
	}

}
