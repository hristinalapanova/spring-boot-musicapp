package com.ga.musicappboot.service;

import com.ga.musicappboot.config.JwtUtil;
import com.ga.musicappboot.model.Song;
import com.ga.musicappboot.model.User;
import com.ga.musicappboot.repository.SongRepository;
import com.ga.musicappboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;

    @Override
    public String signup(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(userRepository.save(user) != null) {
            UserDetails userDetails = loadUserByUsername(user.getUsername());

            return jwtUtil.generateToken(userDetails);
        }

        return null;
    }

    @Override
    public String login(User user) {
        User foundUser = userRepository.login(user.getUsername(), user.getPassword());
        if(foundUser != null &&
                bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            UserDetails userDetails = loadUserByUsername(foundUser.getUsername());

            return jwtUtil.generateToken(userDetails);
        }

        return null;
    }


    @Override
    public List<Song> addSong(Long songId, String username) {
        User user = userRepository.findByUsername(username);
        Song song = songRepository.findById(String.valueOf(songId)).orElse(null);
        user.addSong(song);
        userRepository.save(user);
        return user.getSongs();
    }

    @Override
    public List<Song> listSongs(String username) {
        return userRepository.findByUsername(username).getSongs();

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Autowired
    JwtUtil jwtUtil;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user==null)
            throw new UsernameNotFoundException("Unkknown user: " +username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        return authorities;
    }

    @Override
    public Long deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        userRepository.delete(user);
        return userId;
    }

    @Override
    public User updateUser(User user, Long userId) {
        User user1 = userRepository.findById(userId).orElse(null);
        user.setPassword(user1.getPassword());
        return userRepository.save(user1);
    }

    @Override
    public List<Song> getSongs(String username) {
        User user = userRepository.findByUsername(username);
        return user.getSongs();
    }


}
