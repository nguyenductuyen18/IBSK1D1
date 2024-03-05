package com.codegym.applicationsandlibraries.repository;

import com.codegym.applicationsandlibraries.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User,Integer> {
}
