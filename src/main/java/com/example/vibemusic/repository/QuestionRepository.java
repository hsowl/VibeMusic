package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Question;
import com.example.vibemusic.repository.search.QuestSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestSearch {
        Page<Question> findByQWriterContaining(String writerKeyword, Pageable pageable);

//        Page<Question> findByUsername(String username, Pageable pageable);


//    @Query("SELECT q FROM Question q WHERE (:types IS NULL OR q.qWriter IN :types) AND " +
//            "(:keyword IS NULL OR q.qTitle LIKE %:keyword% OR q.qContent LIKE %:keyword%)")
//    Page<Question> searchAll(@Param("types") String[] types,
//                             @Param("keyword") String keyword,
//                             Pageable pageable);
}
