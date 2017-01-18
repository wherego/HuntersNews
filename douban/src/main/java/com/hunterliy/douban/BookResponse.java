package com.hunterliy.douban;

import java.util.List;

public class BookResponse {
    public int count;
    public int start;
    public int total;
    public List<Book> books;

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public int getTotal() {
        return total;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}