package com.ga.musicappboot.service;

import com.ga.musicappboot.model.Song;
import com.ga.musicappboot.model.User;
import com.ga.musicappboot.repository.SongRepository;
import com.ga.musicappboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongRepository songRepository;


    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> addListener(long songId, String username) {
        User user = userRepository.findByUsername(username);
        Song song = (Song) songRepository.findById(String.valueOf(songId)).orElse(null);
        song.addListener(user);
        return songRepository.save(song).getListeners();
    }

    @Override
    public List<Song> songList() {

        return (List<Song>) songRepository.findAll();
    }

    @Override
    public Song createSong(Song song) {

        return songRepository.save(song);
    }

    @Override
    public Song updateSong(Song song, Long songId) {
        Song savedSong = songRepository.findById(String.valueOf(songId)).orElse(null);
        savedSong.setTitle(song.getTitle());
        savedSong.setLength(song.getLength());
        return savedSong;

    }

    @Override
    public long deleteSong(Long songId) {
        Song savedSong = songRepository.findById(String.valueOf(songId)).orElse(null);
        songRepository.delete(savedSong);
        return savedSong.getSongId();
    }

    @Override
    public List<User> listeners(Long songId) {
        Song savedSong = songRepository.findById(String.valueOf(songId)).orElse(null);
        return savedSong.getListeners();
    }



}
