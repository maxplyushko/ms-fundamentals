package com.epam.songservice.mapper;

import com.epam.songservice.domain.dto.SongDto;
import com.epam.songservice.domain.entity.Song;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SongMapper {

  SongDto entityToDto(Song song);
  Song dtoToEntity(SongDto dto);
}
