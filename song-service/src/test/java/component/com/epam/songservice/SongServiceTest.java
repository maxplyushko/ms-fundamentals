package component.com.epam.songservice;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {
    "component.com.epam.songservice.steps",
    "component.com.epam.songservice.config"}, plugin = {"pretty"})
public class SongServiceTest {
}
