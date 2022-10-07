package component;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {"component.steps",
    "component.config"}, plugin = {"pretty"})
public class ResourceServiceTest {

}
