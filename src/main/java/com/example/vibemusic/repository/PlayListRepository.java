package com.example.vibemusic.repository;

import com.example.vibemusic.domain.PlayList;
import com.example.vibemusic.security.dto.MemberSecurityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Long> {

//    void deleteByPlNoAndMusics()

//    @Query("select pl from PlayList pl \n" +
//            "join playlist_music pm on pm.plNo = pl.plNo  \n" +
//            "join Music m on m.no = pm.no \n" +
//            "where pl.mid = :mid")

    @Query("select pl from PlayList pl where pl.member = 'jimin2'")
    List<PlayList> findByMember_Mid(@AuthenticationPrincipal MemberSecurityDTO mid);

//    List<PlayList> findAll(Optional<Member> listsByMid);
}
