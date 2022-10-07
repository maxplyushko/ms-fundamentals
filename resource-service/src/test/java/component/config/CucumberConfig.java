package component.config;

import com.epam.resourceservice.ResourceServiceApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {ResourceServiceApplication.class})
public class CucumberConfig {

}
