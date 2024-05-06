package com.kodnest.tunehub.service;

import org.springframework.stereotype.Service;

import java.util.*;
import com.kodnest.tunehub.entity.Song;
@Service
public interface SongService {
	 void saveSong(Song song);

	boolean songExists(String name);

	List<Song> fetchAllSongs();


	void updateSong(Song song);
	


}
