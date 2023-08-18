//package com.example.vibemusic.repository.search;
//
//import com.example.vibemusic.domain.QQuestion;
//import com.example.vibemusic.domain.Question;
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.jpa.JPQLQuery;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//public class QuestSearchImpl {
//
//
//    @Override
//    public Page<Question> searchAll(String[] types, String keyword, Pageable pageable) {
//        QQuestion question = QQuestion.question;
//        JPQLQuery<Question> query = from(question);
//
//        if ((types != null && types.length > 0) && keyword != null) {
//            BooleanBuilder booleanBuilder = new BooleanBuilder();
//
//            for (String type : types) {
//                switch (type) {
//                    case "t":
//                        booleanBuilder.or(question.qTitle.contains(keyword));
//                        break;
//                    case "w" :
//                        booleanBuilder.or(question.qWriter.contains(keyword));
//                        break;
//                    case "c" :
//                        booleanBuilder.or(question.qContent.contains(keyword));
//                        break;
//                }
//            }
//            query.where(booleanBuilder);
//        }
//        System.out.println("query ============================>"+ query);
//
//        query.where(question.qNo.gt(0L));
//        System.out.println("query ============================>"+ query);
//
//        this.getQuerydsl().applyPagination(pageable,query);
//
//        List<Question> list = query.fetch();
//
//        long count = query.fetchCount();
//
//        return new PageImpl<>(list,pageable,count);
//    }
//}
//
//}