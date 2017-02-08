package com.jaxio.demo.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jaxio.demo.domain.Book;
import com.jaxio.demo.repository.BookRepository;
import com.jaxio.demo.repository.search.BookSearchRepository;
import com.jaxio.demo.utils.JsonUtils;

import static org.hamcrest.Matchers.hasSize;

public class BookResourceUnitTest {

	private final Logger log = LoggerFactory.getLogger(BookResourceUnitTest.class);
	
	private MockMvc mockMvc;

	@Inject
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private BookSearchRepository bookSearchRepository;
	
    @InjectMocks
    private BookResource bookResource;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(bookResource)
                .addFilters(new CorsFilter())
                /* to handle Pageable automatically */
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .build();
    }
    

    /**
     * Tests the create method on BookResource.
     */
    @Test
    @Ignore
    public void testCreate() {
    	Book book = new Book();
    	book.setId("1");
        
        try {
        	
	        when(bookRepository.save(book)).thenReturn(book);
	        
	        mockMvc.perform(put("/api/books/")
	        		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	        		/* add a request parameter */
	                .content(JsonUtils.convertObjectToJsonBytes(book))
	                )
	                .andExpect(status().isOk())
	                /* tp print the PUT result */
	                .andDo(print())
	                // to validate json result content
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$.price", nullValue()));
	        
	        verify(bookRepository, times(1)).save(book);
	        verifyNoMoreInteractions(bookRepository);
        } catch (Exception e) {
        	log.error("", e);
        	fail();        	
        }
    }
    
    /**
     * Tests the update method on BookResource.
     */
    @Test
    @Ignore
    public void testUpdate() {
    	Book book = new Book();
    	book.setId("1");
        
        try {
        	
	        when(bookRepository.save(book)).thenReturn(book);
	        
	        mockMvc.perform(put("/api/books/")
	        		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	                .content(JsonUtils.convertObjectToJsonBytes(book))
	                )
	                .andExpect(status().isOk())
	                /* tp print the PUT result */
	                .andDo(print())
	                // to validate json result content
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$.price", nullValue()));
	        
	        verify(bookRepository, times(1)).save(book);
	        verifyNoMoreInteractions(bookRepository);
        } catch (Exception e) {
        	log.error("", e);
        	fail();        	
        }
    }
    
    /**
     * Tests the findAll method on BookResource.
     */
    @Test
    @Ignore
    public void testFindAll() {
    	Book book = new Book();
    	book.setId("1");
        List<Book> books = new ArrayList<Book>();
        books.add(book);
        book = new Book();
    	book.setId("2");
        books.add(book);
        
        try {
        	
	        when(bookRepository.findAll()).thenReturn(books);
	        
	        mockMvc.perform(get("/api/books/"))
	                .andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))	                
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[0].id", is("1")))
	                .andExpect(jsonPath("$[1].id", is("2")));
	        
	        verify(bookRepository, times(1)).findAll();
	        verifyNoMoreInteractions(bookRepository);
        } catch (Exception e) {
        	log.error("", e);
        	fail();        	
        }
    }
    
    /**
     * Tests the findAllByPage method on BookResource.
     */
    @Test
    public void testFindAllByPage() {
    	Book book = new Book();
    	book.setId("1");
        final List<Book> books = new ArrayList<Book>();
        books.add(book);
        book = new Book();
    	book.setId("2");
        books.add(book);
        
        try {
        	
			Page<Book> expectedPage = new PageImpl<Book>(books);
	        when(bookRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);
	        //new PageableImpl();
	        
	        mockMvc.perform(get("/api/books/bypage")
	        		.param("page", "0")
	        		.param("size", "2")
	        		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	        		/* add a request parameter */
	                .content(JsonUtils.convertObjectToJsonBytes(expectedPage))
	                )
	                .andExpect(status().isOk())
	                .andDo(print());/*
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))	                
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[0].id", is("1")))
	                .andExpect(jsonPath("$[1].id", is("2")));*/
	        
	        verify(bookRepository, times(1)).findAll();
	        verifyNoMoreInteractions(bookRepository);
        } catch (Exception e) {
        	log.error("", e);
        	fail();        	
        }
    }
    
    /**
     * Tests the count method on BookResource.
     */
    @Test
    @Ignore
    public void testCount() {
    	final String count = "1";
        
        try {
        	
	        when(bookRepository.count()).thenReturn(Long.valueOf(count));
	        
	        mockMvc.perform(get("/api/books/count"))
	                .andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))	                
	                .andExpect(content().string(count));
	        
	        verify(bookRepository, times(1)).count();
	        verifyNoMoreInteractions(bookRepository);
        } catch (Exception e) {
        	log.error("", e);
        	fail();        	
        }
    }
}
