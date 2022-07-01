package com.epam.resourceprocessor.parser.impl;

import com.epam.commons.dto.SongContentDto;
import com.epam.commons.dto.SongDto;
import com.epam.resourceprocessor.parser.SongParser;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import lombok.SneakyThrows;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Component;

@Component
public class Mp3AudioParser implements SongParser {

  private static final String SONG_TITLE = "dc:title";
  private static final String SONG_ARTIST = "xmpDM:albumArtist";
  private static final String SONG_ALBUM = "xmpDM:album";
  private static final String SONG_DURATION = "xmpDM:duration";
  private static final String SONG_RELEASE_DATE = "xmpDM:releaseDate";
  private static final DecimalFormat SONG_DURATION_FORMAT = new DecimalFormat("0.00");
  private static final int ONE_MINUTE_IN_SECONDS = 60;

  @Override
  @SneakyThrows
  public SongDto parse(SongContentDto songContent) {
    Mp3Parser mp3Parser = new Mp3Parser();
    InputStream stream = new ByteArrayInputStream(songContent.getSong());
    Metadata metadata = new Metadata();
    mp3Parser.parse(stream, new BodyContentHandler(), metadata, new ParseContext());
    return buildSong(songContent.getResourceId(), metadata);
  }

  private SongDto buildSong(long resourceId, Metadata metadata) {
    return SongDto.builder()
        .name(metadata.get(SONG_TITLE))
        .artist(metadata.get(SONG_ARTIST))
        .album(metadata.get(SONG_ALBUM))
        .length(formatSongDuration(metadata.get(SONG_DURATION)))
        .year(metadata.get(SONG_RELEASE_DATE))
        .resourceId(resourceId)
        .build();
  }

  private String formatSongDuration(String seconds) {
    final double minutes = Double.parseDouble(seconds) / ONE_MINUTE_IN_SECONDS;
    return String.valueOf(SONG_DURATION_FORMAT.format(minutes));
  }
}
