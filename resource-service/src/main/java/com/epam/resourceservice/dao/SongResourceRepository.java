package com.epam.resourceservice.dao;

import com.epam.resourceservice.domain.entity.SongResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongResourceRepository extends CrudRepository<SongResource, Long> {

}
