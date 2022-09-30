/* @SpringBootApplication is a convenience annotation that adds al of the following:

@Configuration: Tags the class as a source of bean definitions for the application context.

@EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings,
other beans, and various property settings. For example, if spring-webmvc is on the classpath,
 this annotation flags the application as a web application and activates key behaviors,
 such as setting up a DispatcherServlet.*/

/* The AccessingDataJpaApplication class includes a demo() method that puts the CustomerRepository through a few tests.
First, it fetches the CustomerRepository from the Spring application context. Then it saves a handful of Customer objects,
demonstrating the save() method and setting up some data to work with. Next, it calls findAll() to fetch all Customer objects
 from the database. Then it calls findById() to fetch a single Customer by its ID. Finally, it calls findByLastName() to find
  all customers whose last name is "Bauer". The demo() method returns a CommandLineRunner bean that automatically runs the code
   when the application launches.*/

package com.example.accessingdatawithjpa;

import com.example.accessingdatawithjpa.model.Customer;
import com.example.accessingdatawithjpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataWithJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataWithJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataWithJpaApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(CustomerRepository repository){
        return (args) -> {
            // save a few Customers
            repository.save(new Customer("kambal", "Muhindo"));
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("joel", "johansson"));
            repository.save(new Customer("layton", "ahmed"));
            repository.save(new Customer("lenny", "luva"));

            // bring all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()){
                log.info(customer.toString());
            }
            log.info("");

            // // bring an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // bring customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}
