package com.example.mvccrud.dao;

import com.example.mvccrud.entity.Author;
import com.example.mvccrud.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author,Integer> {
}