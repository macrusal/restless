/**
 * 
 */
package br.com.leraning.restless.config;

import br.com.leraning.restless.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


/**
 * @author msalvador
 *
 */
@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	DBService dbService;
	
	@Bean
	public boolean instantiateDataase() {
		dbService.instantiateTestDataBase();
		return true;
	}

}
