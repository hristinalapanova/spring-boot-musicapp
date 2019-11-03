package com.ga.musicappboot.service;

import com.ga.musicappboot.model.Song;
import com.ga.musicappboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public String signup(User user);

    public List<Song> addSong(Long id, String username);

    public List<Song> listSongs(String username);

    public User findByUsername(String username);

    public String login(User user);

    public Long deleteUser(Long userId);

    public User updateUser(User user, Long userId);

    public List<Song> getSongs(String username);
}
