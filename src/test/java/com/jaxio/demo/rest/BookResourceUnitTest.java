package com.jaxio.demo.rest;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.fail;
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

import java.util.Arrays;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jaxio.demo.domain.Book;
import com.jaxio.demo.repository.BookRepository;
import com.jaxio.demo.repository.search.BookSearchRepository;
import com.jaxio.demo.utils.JsonUtils;

public class BookResourceUnitTest {

	private final Logger log = LoggerFactory.getLogger(BookResourceUnitTest.class);
	
	private MockMvc mockMvc;

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
                .build();
    }
    
    @Test
    public void testBookFindAll() {
    	Book book = new Book();
    	book.setId("1");
        List<Book> books = Arrays.asList(
                new Book());
        
        try {
        	
	        when(bookResource.findAll()).thenReturn(new ResponseEntity<>(HttpStatus.OK));
	        
	        mockMvc.perform(get("/api/books/"))
	                .andExpect(status().isOk())
	                /*.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[0].id", is(1)))
	                .andExpect(jsonPath("$[0].username", is("Daenerys Targaryen")))
	                .andExpect(jsonPath("$[1].id", is(2)))
	                .andExpect(jsonPath("$[1].username", is("John Snow")))*/;
	        
	        verify(bookResource, times(1)).findAll();
	        verifyNoMoreInteractions(bookResource);
        } catch (Exception e) {
        	log.error("", e);
        	fail();        	
        }
    }
    
    @Test
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
	                /*.andExpect(jsonPath("$", hasSize(2)))*/
	                .andExpect(jsonPath("$.price", nullValue()));
	                /*
	                .andExpect(jsonPath("$[0].username", is("Daenerys Targaryen")))
	                .andExpect(jsonPath("$[1].id", is(2)))
	                .andExpect(jsonPath("$[1].username", is("John Snow")))*/;
	        
	        verify(bookRepository, times(1)).save(book);
	        verifyNoMoreInteractions(bookRepository);
        } catch (Exception e) {
        	log.error("", e);
        	fail();        	
        }
    }
}
