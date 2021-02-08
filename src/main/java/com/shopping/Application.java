package com.shopping;

import com.shopping.models.*;
import com.shopping.repository.*;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application{


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
