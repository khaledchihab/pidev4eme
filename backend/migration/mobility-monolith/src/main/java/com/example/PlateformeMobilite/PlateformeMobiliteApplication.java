package com.example.PlateformeMobilite;

import com.example.PlateformeMobilite.Services.FormService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient

public class PlateformeMobiliteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlateformeMobiliteApplication.class, args);

	}
	}


//		UserDetails userDetails =
//				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String ud = userDetails.getUsername();
//		System.out.println(ud);
// userDetails.getPassword()
// userDetails.getAuthorities()



