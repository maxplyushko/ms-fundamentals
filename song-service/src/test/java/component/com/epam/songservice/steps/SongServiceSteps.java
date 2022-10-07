package component.com.epam.songservice.steps;

import static org.junit.Assert.assertEquals;

import com.epam.commons.dto.SongDto;
import com.epam.songservice.dao.SongRepository;
import com.epam.songservice.domain.entity.Song;
import com.epam.songservice.mapper.SongMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class SongServiceSteps {

  @Autowired
  private SongRepository songRepository;

  @Autowired
  private SongMapper songMapper;

  private Song song;

  @Given("A song with name {string}")
  public void a_song_with_id_and_title(String name) {
    SongDto songDto = SongDto.builder()
        .name(name)
        .build();
    song = songMapper.dtoToEntity(songDto);
  }

  @When("We add that song")
  public void we_add_that_song() {
    songRepository.save(song);
  }

  @Then("Song with name {string} should be added")
  public void song_with_id_should_be_added(String name) {
    final Optional<Song> result = songRepository.findByName(name);
    assertEquals(song.getName(), result.get().getName());
  }
}
