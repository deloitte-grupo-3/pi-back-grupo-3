package br.com.exlivraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.exlivraria.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
