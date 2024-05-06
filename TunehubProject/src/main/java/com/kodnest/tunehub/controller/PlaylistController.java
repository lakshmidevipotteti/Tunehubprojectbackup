package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.PlaylistService;
import com.kodnest.tunehub.service.SongService;


@Controller
public class PlaylistController {
	@Autowired
	PlaylistService playlistService;
	
	@Autowired
	SongService songService;
	
	
	
	@GetMapping("/createplaylists")
	public String createPlaylist(Model model) {
		List<Song> songList=songService.fetchAllSongs();
		
		model.addAttribute("songs",songList);
		return "createplaylist";
	
	}
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist,RedirectAttributes redirectAttributes) {
		playlistService.addPlaylist(playlist);
		
		
		
		List<Song> songs=playlist.getSongs();
		
		
		for(Song song :songs) {
			song.getPlaylists().add(playlist);
			songService.updateSong(song);
		}
		
		
		return "adminhome";
	}
		
		@GetMapping("/viewplaylist")
		public  String viewplaylist(Model model) {
			
			List<Playlist> allplaylists=playlistService.fetchAllPlaylists();
			model.addAttribute("playlists",allplaylists);
		
			return "viewplaylist";
			
		}
	
	

}
