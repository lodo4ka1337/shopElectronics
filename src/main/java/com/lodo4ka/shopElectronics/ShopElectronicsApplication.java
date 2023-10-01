package com.lodo4ka.shopElectronics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShopElectronicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopElectronicsApplication.class, args);
	}

}
