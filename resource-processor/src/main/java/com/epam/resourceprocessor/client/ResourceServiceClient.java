package com.epam.resourceprocessor.client;

import com.epam.commons.dto.SongContentDto;

public interface ResourceServiceClient {

  SongContentDto getSongContent(String songId);
}
