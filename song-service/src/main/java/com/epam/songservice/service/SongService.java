package com.epam.songservice.service;

import com.epam.songservice.domain.entity.Song;
import java.util.List;

public interface SongService extends CrudService<Song, Long> {

  List<Long> deleteByIds(List<Long> ids);
}
