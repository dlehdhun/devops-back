package com.example.springBaseCode.dto;

import java.util.List;

public class BookListResponse {
    private List<BookDto> books;
    private int totalCount;

    public BookListResponse() {
    }

    public BookListResponse(List<BookDto> books, int totalCount) {
        this.books = books;
        this.totalCount = totalCount;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
