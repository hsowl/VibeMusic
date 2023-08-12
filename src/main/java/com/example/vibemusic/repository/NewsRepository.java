package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {


//    Page<News> searchAll(String[] types, String keyword, Pageable pageable);
}
