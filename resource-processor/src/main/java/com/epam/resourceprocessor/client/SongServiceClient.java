package com.epam.resourceprocessor.client;

import com.epam.commons.dto.SongDto;

public interface SongServiceClient {

  SongDto saveSongMetadata(SongDto metaData);
}
