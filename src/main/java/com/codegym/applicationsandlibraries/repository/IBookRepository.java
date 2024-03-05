package com.codegym.applicationsandlibraries.repository;

import com.codegym.applicationsandlibraries.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface IBookRepository extends CrudRepository<Book,Integer> {
}
