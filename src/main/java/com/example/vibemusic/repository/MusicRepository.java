package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.repository.search.MusicSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> , MusicSearch {
}
