package component.com.epam.songservice.config;

import com.epam.songservice.SongServiceApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("qa")
@CucumberContextConfiguration
@PropertySource("classpath:application-test.yaml")
@SpringBootTest(classes = {SongServiceApplication.class})
public class CucumberConfig {

}
