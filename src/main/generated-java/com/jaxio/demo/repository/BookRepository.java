/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template angular-lab:springboot/src/main/java/repository/EntityRepository.e.vm.java
 */
package com.jaxio.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.jaxio.demo.domain.Book;

/**
 * Book repository.
 * 
 * @author generated by Celerio
 *
 */
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(:title) OR LOWER(b.description) LIKE LOWER(:description) OR b.price = :price")
    public List<Book> search(@Param("title") String title, @Param("description") String description, @Param("price") BigDecimal price);

}