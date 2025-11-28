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
    public List<HashMap<String, String>> bookList(HashMap<String, String> params) {
        return bookMapper.bookList(params);
    }

    @Override
    public int bookListCount(HashMap<String, String> params) {
        return bookMapper.bookListCount(params);
    }

    @Override
    public List<HashMap<String, String>> getLearningFormList(HashMap<String, String> params) {
        return bookMapper.getLearningFormList(params);
    }

    @Override
    public List<HashMap<String, String>> getCaSubjectTeacherList(HashMap<String, String> params) {
        return bookMapper.getCaSubjectTeacherList(params);
    }

    @Override
    public int getCaBookSeq(HashMap<String, String> params) {
        return bookMapper.getCaBookSeq(params);
    }

    @Override
    public String getCaBookRscId(HashMap<String, String> params) {
        return bookMapper.getCaBookRscId(params);
    }

    @Override
    public void bookInsert(HashMap<String, String> params) {
        bookMapper.bookInsert(params);
    }

    @Override
    public List<HashMap<String, String>> bookViewList(HashMap<String, String> params) {
        return bookMapper.bookViewList(params);
    }

    @Override
    public List<HashMap<String, String>> bookView(HashMap<String, String> params) {
        return bookMapper.bookView(params);
    }

    @Override
    public int bookUseCheck(HashMap<String, String> params) {
        return bookMapper.bookUseCheck(params);
    }

    @Override
    public void bookUpdate(HashMap<String, String> params) {
        bookMapper.bookUpdate(params);
    }

    @Override
    public void bookDelete(HashMap<String, String> params) {
        bookMapper.bookDelete(params);
    }

    @Override
    public List<HashMap<String, String>> bookSellList(HashMap<String, String> params) {
        return bookMapper.bookSellList(params);
    }

    @Override
    public int bookSellListCount(HashMap<String, String> params) {
        return bookMapper.bookSellListCount(params);
    }

    @Override
    public List<HashMap<String, String>> bookSellListExcel(HashMap<String, String> params) {
        return bookMapper.bookSellListExcel(params);
    }
}
