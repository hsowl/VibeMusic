package com.example.vibemusic.repository.search;

import com.example.vibemusic.domain.EventBoard;
import com.example.vibemusic.domain.QEventBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class EventBoardSearchImpl extends QuerydslRepositorySupport implements EventBoardSearch {

    public EventBoardSearchImpl() {
        super(EventBoard.class);
    }

    @Override
    public Page<EventBoard> searchOne(Pageable pageable) {

        QEventBoard qEventBoard = QEventBoard.eventBoard;

        JPQLQuery<EventBoard> query = from(qEventBoard);

        query.where(qEventBoard.title.contains("1"));

        this.getQuerydsl().applyPagination(pageable, query);

        List<EventBoard> list = query.fetch();

        Long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<EventBoard> searchAll(String[] types, String keyword, Pageable pageable) {

        QEventBoard qEventBoard = QEventBoard.eventBoard;
        JPQLQuery<EventBoard> query = from(qEventBoard);

        if( (types != null && types.length>0) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type: types) {
                switch (type) {
                    case "t" :
                        booleanBuilder.or(qEventBoard.title.contains(keyword));
                        break;
                    case "c" :
                        booleanBuilder.or(qEventBoard.content.contains(keyword));
                        break;
                    case "w" :
                        booleanBuilder.or(qEventBoard.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(qEventBoard.ebno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<EventBoard> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
