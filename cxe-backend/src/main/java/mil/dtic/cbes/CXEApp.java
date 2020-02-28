package mil.dtic.cbes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
//import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
//@PropertySource("file:///d2/config/cxe/application.properties")
@PropertySources({
	@PropertySource("classpath:application.properties"),
	@PropertySource("classpath:config.properties")})
public class CXEApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CXEApp.class, args);
	}
}
