package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {

//    void deleteByPlNoAndMusics()

    List<PlayList> findByMember_Mid(String mid);

}
