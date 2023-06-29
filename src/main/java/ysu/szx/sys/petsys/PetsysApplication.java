package ysu.szx.sys.petsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan
@SpringBootApplication
public class PetsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsysApplication.class, args);
	}

}
