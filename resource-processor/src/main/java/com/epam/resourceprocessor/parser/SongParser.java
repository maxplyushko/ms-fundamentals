package com.epam.resourceprocessor.parser;

import com.epam.commons.dto.SongContentDto;
import com.epam.commons.dto.SongDto;

public interface SongParser {

  SongDto parse(SongContentDto songContent);
}
