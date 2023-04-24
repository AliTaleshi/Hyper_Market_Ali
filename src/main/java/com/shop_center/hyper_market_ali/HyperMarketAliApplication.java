package com.shop_center.hyper_market_ali;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HyperMarketAliApplication {

	public static void main(String[] args) {
		SpringApplication.run(HyperMarketAliApplication.class, args);
		
	}

	@GetMapping("/hi")
	public HelloClass getHello() {
		return new HelloClass("HelloWorld");
	}

	/**
	 * HelloClass
	 */
	public class HelloClass {
	
		String name;

		HelloClass(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
