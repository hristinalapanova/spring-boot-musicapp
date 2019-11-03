package com.ga.musicappboot.service;

import com.ga.musicappboot.model.Song;
import com.ga.musicappboot.model.User;


import java.util.List;

public interface SongService {

    public List<Song> songList();
    public Song createSong(Song song);
    public Song updateSong(Song Song, Long SongId);
    public long deleteSong(Long SongId);
    public List<User> listeners(Long songId);

    public List<User> addListener(long songId, String username);
}
