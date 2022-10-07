package component.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ResourceServiceSteps {

  private String resourcesApiUrl;
  private ResponseEntity<Long> responseEntity;

  @Given("I set POST resource API endpoint")
  public void i_set_post_resource_api_endpoint() {
    resourcesApiUrl = "http://localhost:8081/resources";
  }

  @When("I send a POST HTTP request with multipart file with {string} name")
  public void i_send_a_post_http_request_with_multipart_file_with(String name)
      throws IOException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();ame));
    HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(form, headers);
    RestTemplate restTemplate = new RestTemplate();
    responseEntity = restTemplate.postForEntity(resourcesApiUrl, httpEntity, Long.class);
  }

  private MultipartFile getTestFile(String name) throws IOException {
    Path path = Paths.get("C:\\Users\\Maksim_Pliushko\\Projects\\ms-fundamentals\\resource-service\\src\\test\\resources\\test-song123.mp3");
    System.out.println("Wooooo" + path.toFile());
    InputStream inputStream = new FileInputStream(path.toFile());
    final ClassPathResource classPathResource = new ClassPathResource();
    return new MockMultipartFile(name, inputStream);
  }

  @Then("id {int} of the song resource should be returned")
  public void id_of_the_song_resource_should_be_returned(Integer int1) {
    responseEntity.getBody();
  }
}
