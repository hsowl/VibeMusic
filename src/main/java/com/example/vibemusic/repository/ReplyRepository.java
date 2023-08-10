package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findReplyByMusic_No(Long no);
}
