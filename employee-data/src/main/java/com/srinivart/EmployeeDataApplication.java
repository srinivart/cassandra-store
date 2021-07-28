package com.srinivart;

import com.srinivart.util.Helper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDataApplication.class, args);

		Helper helper = new Helper();

		// helper.createTable();

		//helper.insertIntoTable();

		//helper.readFromTable();

		//helper.updateTable();

		//helper.deleteFromTable();


		//helper.dropTable();



		//helper.createKeySpace();

		//helper.dropKeyspace();
	}

}
