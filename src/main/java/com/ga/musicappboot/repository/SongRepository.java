package com.ga.musicappboot.repository;

import com.ga.musicappboot.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends CrudRepository<Song, String>{
}
