package com.heeju.bootmvc.repository.search;

import com.heeju.bootmvc.domain.Board;
import com.heeju.bootmvc.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        // JPQLQuery allows to create JPQL written with @Query through code.
        // Through this, where, group by, or join processing is possible.
        // JPQLQuery can be executed using a function called fetch(),
        // and a count query can be executed using fetchCount().
        // Reflect them by adding the following to the search1() code.
        QBoard board = QBoard.board;            // Q domain object
        JPQLQuery<Board> query = from(board);   // select.. from board

        // When using Querydsl, if a situation needs '()' by stating
        // both 'and' or 'or' for instance, utilize BooleanBuilder.
        BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
        booleanBuilder.or(board.title.contains("11")); // title like ...
        booleanBuilder.or(board.content.contains("11")); // content like ...

        query.where(booleanBuilder);
        query.where(board.bno.gt(0L));

//        query.where(board.title.contains("1")); // where title like ...

        //paging
        this.getQuerydsl().applyPagination(pageable, query);
        // query here is a filtering method,
        // pageble is the number of pages and sorting, etc.

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if((types != null && types.length > 0) && keyword != null){ // search condition.
                                                                    // type and keyword are required.

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for(String type: types){

                switch(type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }

            }//end for
            query.where(booleanBuilder);
        }//end if

        //bno > 0
        query.where(board.bno.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
