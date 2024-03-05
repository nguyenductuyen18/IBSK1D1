package com.codegym.applicationsandlibraries.service;

import com.codegym.applicationsandlibraries.model.Book;
import com.codegym.applicationsandlibraries.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepository iBookRepository;
    @Override
    public Iterable<Book> findAll() {
        return iBookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(int id) {
        return iBookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
 return iBookRepository.save(book);
    }

    @Override
    public void deleteById(int id) {
iBookRepository.deleteById(id);
    }



}
