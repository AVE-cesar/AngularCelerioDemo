/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template angular-lab:springboot/src/main/java/rest/EntityResource.e.vm.java
 */
package com.jaxio.demo.rest;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jaxio.demo.domain.Book;
import com.jaxio.demo.repository.BookRepository;
import com.jaxio.demo.repository.search.BookSearchRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/books")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    @Inject
    private BookRepository bookRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Inject
    private BookSearchRepository bookSearchRepository;
    
    /**
     * Create a new Book.
     */
    @ApiOperation(value = "Create a book.")
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> create(@RequestBody Book book) throws URISyntaxException {
        log.debug("Create Book : {}", book);
        Book result = bookRepository.save(book);
        bookSearchRepository.save(book);
        return ResponseEntity.created(new URI("/api/books/" + result.getId())).body(result);
    }

    /**
     * Update Book.
     */
    @ApiOperation(value = "Update a book.")
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> update(@RequestBody Book book) throws URISyntaxException {
        log.debug("Update Book : {}", book);
        if (book.getId() == null) {
            return create(book);
        }
        Book result = bookRepository.save(book);
        bookSearchRepository.save(book);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
    }

    /**
     * Find all Book.
     * WARNING: if your table has got a lot of records, you will face OutOfMemory error.
     */
    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> findAll() throws URISyntaxException {
        log.debug("Find all Books");
        List<Book> list = bookRepository.findAll();
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Find all Book by page.
     */
    @RequestMapping(value = "/bypage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Book> findAllByPage(Pageable pageable) throws URISyntaxException {
        log.debug("Find all by page Books, page: " + pageable.getPageNumber() + ", size: " + pageable.getPageSize());
        Page<Book> page = bookRepository.findAll(pageable);
        log.debug("There are " + page.getTotalElements() + " books.");
        return page;
    }

    /**
     * Find by id Book (for simple key).
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Book> findById(@PathVariable String id) throws URISyntaxException {
        log.debug("Find by id Books : {}.", id);
        
        Book fullyLoadedBook = bookRepository.findOne(id);
        
		return Optional.ofNullable(fullyLoadedBook)
            .map(book -> new ResponseEntity<>(
                book,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete by id Book (for simple key).
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id) throws URISyntaxException {
        log.debug("Delete by id Books : {}.", id);
        bookRepository.delete(id);
        bookSearchRepository.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Mass deletion (for simple key).
     */
    @RequestMapping(value = "/mass/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable String[] id) throws URISyntaxException {
        log.debug("Delete by id Books : {}.", (Object[])id);
        Stream.of(id).forEach(item -> {bookRepository.delete(item); bookSearchRepository.delete(item);}); 
        
        return ResponseEntity.ok().build();
    }

    /**
     * Index all Book.
     */
    @RequestMapping(value = "/indexAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	@Async
    public void indexAllBooks() {
    	log.debug("REST request to index all Books, START");
    	bookRepository.findAll().forEach(p -> {log.debug("indexing");bookSearchRepository.index(p);});
    	
    	PageRequest request = new PageRequest(0, 1000);
        try {
        	Page<Book> page = findAllByPage(request);
        	page.forEach(p -> bookSearchRepository.index(p));
                         
             while (page.hasNext()) {
                    request = new PageRequest(request.getPageNumber() + 1, 1000);
                    
                    log.debug("we are indexing page: " + (request.getPageNumber() + 1));
                    
                    page = findAllByPage(request);
                    page.forEach(p -> bookSearchRepository.index(p));
              }
        } catch (Exception e) {
        	log.error("", e);
        }

        log.debug("REST request to index all Books, EXIT");
    }

    /**
     * Search with ElasticSearch.
     */
    @RequestMapping(value = "/esearch/{query}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> searchBooks(@PathVariable String query) {
        return StreamSupport.stream(bookSearchRepository.search(queryStringQuery(query)).spliterator(), false).collect(Collectors.toList());
    }

    /**
     * Count Book.
     * FIXME: this method should be asynchronous because it can take times to count all records !
     */
    @RequestMapping(value = "/count",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> count() throws URISyntaxException {
        log.debug("Count books");
        long count = bookRepository.count();
        
        return new ResponseEntity<>(count, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Check if a Book exists via its id.
     */
    @RequestMapping(value = "/exists/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> exists(@PathVariable String id) throws URISyntaxException {
    	log.debug("Check book existence via its id: {}.", id);
    	Boolean exists = bookRepository.exists(id);
        
        return new ResponseEntity<>(exists, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Search books.
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Book> search(@RequestBody Book book, Pageable pageable) throws URISyntaxException {
        log.debug("Search books, page: " + pageable.getPageNumber() + ", size: " + pageable.getPageSize());
        log.debug("book: " + book);

        long total = bookRepository.count();

        String sqlMainPart = "select * from (select ID, TITLE, DESCRIPTION, PUBLICATION_DATE, AUTHOR_ID, PRICE, BARCODEID from BOOK where 1=1";
        String sqlSecondaryPart = "";

        List<Object> values = new ArrayList<Object>();

        if (book.getId() != null) {
            sqlSecondaryPart += " and upper(id) like ? ";
            values.add(book.getId().toUpperCase() + "%");
        }
        if (book.getTitle() != null) {
            sqlSecondaryPart += " and upper(title) like ? ";
            values.add(book.getTitle().toUpperCase() + "%");
        }
        if (book.getDescription() != null) {
            sqlSecondaryPart += " and upper(description) like ? ";
            values.add(book.getDescription().toUpperCase() + "%");
        }
        if (book.getPublicationDate() != null) {
            sqlSecondaryPart += " and publicationDate = ? ";
            values.add(book.getPublicationDate());
        }
        if (book.getAuthorId() != null) {
            sqlSecondaryPart += " and authorId = ? ";
            values.add(book.getAuthorId());
        }
        if (book.getPrice() != null) {
            sqlSecondaryPart += " and price = ? ";
            values.add(book.getPrice());
        }
        if (book.getBarcodeid() != null) {
            sqlSecondaryPart += " and barcodeid = ? ";
            values.add(book.getBarcodeid());
        }

        sqlSecondaryPart += ") where rownum <= ?";
        values.add(pageable.getPageSize());

        log.debug("SQL: " + sqlMainPart + " " + sqlSecondaryPart);
        List<Book> books = jdbcTemplate.query(sqlMainPart + " " + sqlSecondaryPart, values.toArray(), new BeanPropertyRowMapper<Book>(Book.class));

        Page<Book> page = new PageImpl<Book>(books, pageable, total);

        return page;
    }

}