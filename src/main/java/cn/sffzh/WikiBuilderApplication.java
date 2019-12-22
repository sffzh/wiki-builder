package cn.sffzh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WikiBuilderApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WikiBuilderApplication.class);
//		app.addListeners(new ApplicationPreparedListener());
		app.run(args);
	}
}
