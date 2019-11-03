package com.ga.musicappboot.Controller;

import com.ga.musicappboot.model.JwtResponse;
import com.ga.musicappboot.model.Song;
import com.ga.musicappboot.model.User;
import com.ga.musicappboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }

    @PutMapping("/update/{id}")
    public User update(@RequestAttribute("id") Long userId, @RequestBody User user) {
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@RequestAttribute("id") Long userId) {
        return userService.deleteUser(userId);
    }

    @PutMapping("/{username}/addsong/{songId}")
    public List<Song> addSong(@RequestAttribute("username") String username, @RequestAttribute("songId") Long songId)
    {
        return userService.addSong(songId, username);
    }

    @GetMapping("/{username}/songs")
    public List<Song>songList(@RequestAttribute("username") String username){
        return userService.getSongs(username);
    }
}

