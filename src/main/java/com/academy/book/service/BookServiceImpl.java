package com.academy.book.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.BookMapper;

/**
 * Book Service Implementation
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<HashMap<String, String>> bookList(BookVO bookVO) {
        return bookMapper.bookList(bookVO);
    }

    @Override
    public int bookListCount(BookVO bookVO) {
        return bookMapper.bookListCount(bookVO);
    }

    @Override
    public List<HashMap<String, String>> getLearningFormList(BookVO bookVO) {
        return bookMapper.getLearningFormList(bookVO);
    }

    @Override
    public List<HashMap<String, String>> getCaSubjectTeacherList(BookVO bookVO) {
        return bookMapper.getCaSubjectTeacherList(bookVO);
    }

    @Override
    public int getCaBookSeq(BookVO bookVO) {
        return bookMapper.getCaBookSeq(bookVO);
    }

    @Override
    public String getCaBookRscId(BookVO bookVO) {
        return bookMapper.getCaBookRscId(bookVO);
    }

    @Override
    public void bookInsert(BookVO bookVO) {
        bookMapper.bookInsert(bookVO);
    }

    @Override
    public List<HashMap<String, String>> bookViewList(BookVO bookVO) {
        return bookMapper.bookViewList(bookVO);
    }

    @Override
    public List<HashMap<String, String>> bookView(BookVO bookVO) {
        return bookMapper.bookView(bookVO);
    }

    @Override
    public int bookUseCheck(BookVO bookVO) {
        return bookMapper.bookUseCheck(bookVO);
    }

    @Override
    public void bookUpdate(BookVO bookVO) {
        bookMapper.bookUpdate(bookVO);
    }

    @Override
    public void bookDelete(BookVO bookVO) {
        bookMapper.bookDelete(bookVO);
    }

    @Override
    public List<HashMap<String, String>> bookSellList(BookVO bookVO) {
        return bookMapper.bookSellList(bookVO);
    }

    @Override
    public int bookSellListCount(BookVO bookVO) {
        return bookMapper.bookSellListCount(bookVO);
    }

    @Override
    public List<HashMap<String, String>> bookSellListExcel(BookVO bookVO) {
        return bookMapper.bookSellListExcel(bookVO);
    }
}
