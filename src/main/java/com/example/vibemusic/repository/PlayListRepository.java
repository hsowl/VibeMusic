package com.example.vibemusic.repository;

import com.example.vibemusic.domain.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {
}
