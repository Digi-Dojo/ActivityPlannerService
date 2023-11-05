package it.unibz.digidojo.activityplannerservice;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories({"it.unibz.digidojo.activityplannerservice.domain"})
public class ActivityPlannerServiceApplication {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(ActivityPlannerServiceApplication.class, args);
        System.out.printf(
                "Server running on %s",
                InetAddress.getLocalHost().getHostAddress()
        );
    }
}