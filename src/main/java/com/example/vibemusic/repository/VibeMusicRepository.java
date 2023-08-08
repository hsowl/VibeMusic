package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VibeMusicRepository extends JpaRepository<Music, Long> {
}
