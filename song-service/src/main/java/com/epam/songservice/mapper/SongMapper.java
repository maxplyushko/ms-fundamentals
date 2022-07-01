package com.epam.songservice.mapper;

import com.epam.commons.dto.SongDto;
import com.epam.songservice.domain.entity.Song;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SongMapper {

  SongDto entityToDto(Song song);
  Song dtoToEntity(SongDto dto);
  List<SongDto> entityToDto(List<Song> songs);
}
