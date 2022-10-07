package com.epam.songservice.service.impl

import com.epam.songservice.dao.SongRepository
import com.epam.songservice.domain.entity.Song
import com.epam.songservice.service.SongService
import spock.lang.Specification

class SongServiceImplSpec extends Specification {

    SongRepository songRepository = Mock()

    SongService songService

    Song song

    void setup() {
        songService = new SongServiceImpl(songRepository)
        song = new Song(1, 'Test song', 'Test artist', 'Album', '2:00', '1996', 5L)
    }

    def "Create"() {
        when:
        Song result = songService.create(song)

        then:
        1 * songRepository.save(song) >> song
        result == song
    }
}
