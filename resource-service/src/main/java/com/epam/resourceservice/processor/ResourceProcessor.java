package com.epam.resourceservice.processor;

import com.epam.commons.dto.SongContentDto;
import com.epam.resourceservice.domain.entity.SongResource;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ResourceProcessor {

  SongResource upload(MultipartFile song);

  SongContentDto download(Long songId);

  List<Long> delete(List<Long> songIds);
}
