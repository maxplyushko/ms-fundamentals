package com.epam.resourceprocessor.processor;

import java.io.FileInputStream;
import lombok.SneakyThrows;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Mp3Processor {

  @SneakyThrows
  public void parse(MultipartFile song) {
    BodyContentHandler handler = new BodyContentHandler();
    Metadata metadata = new Metadata();
    FileInputStream stream = (FileInputStream) song.getInputStream();
    ParseContext context = new ParseContext();

    Mp3Parser mp3Parser = new Mp3Parser();
    mp3Parser.parse(stream, handler, metadata, context);
  }
}
