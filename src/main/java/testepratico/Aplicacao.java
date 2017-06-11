package testepratico;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Aplicacao {

	public static void main(String[] args) {
		SpringApplication.run(Aplicacao.class, args);

	}
	
	@Bean
	public DataSource datasource(){
		DriverManagerDataSource dataSourceH2 = new DriverManagerDataSource();
		dataSourceH2.setDriverClassName("org.h2.Driver");
		dataSourceH2.setUrl("jdbc:h2:./testepratico");
		dataSourceH2.setUsername("root");
		dataSourceH2.setPassword("root");
		
		return dataSourceH2;
	}
	
}
