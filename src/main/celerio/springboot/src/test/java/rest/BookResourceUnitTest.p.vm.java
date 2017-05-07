$output.javaTest("${configuration.rootPackage}.rest", "BookResourceUnitTest")##

$output.require("static org.hamcrest.CoreMatchers.is")##
$output.require("static org.hamcrest.CoreMatchers.nullValue")##
$output.require("static org.hamcrest.Matchers.hasSize")##
$output.require("static org.junit.Assert.fail")##
$output.require("static org.mockito.Matchers.any")##
$output.require("static org.mockito.Mockito.times")##
$output.require("static org.mockito.Mockito.verify")##
$output.require("static org.mockito.Mockito.verifyNoMoreInteractions")##
$output.require("static org.mockito.Mockito.when")##
$output.require("static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get")##
$output.require("static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put")##
$output.require("static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print")##
$output.require("static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content")##
$output.require("static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath")##
$output.require("static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status")##

$output.require("java.util.ArrayList")##
$output.require("java.util.List")##

$output.require("javax.inject.Inject")##

$output.require("org.apache.catalina.filters.CorsFilter")##
$output.require("org.junit.Before")##
$output.require("org.junit.Ignore")##
$output.require("org.junit.Test")##
$output.require("org.mockito.InjectMocks")##
$output.require("org.mockito.Mock")##
$output.require("org.mockito.MockitoAnnotations")##
$output.require("org.slf4j.Logger")##
$output.require("org.slf4j.LoggerFactory")##
$output.require("org.springframework.data.domain.Page")##
$output.require("org.springframework.data.domain.PageImpl")##
$output.require("org.springframework.data.domain.Pageable")##
$output.require("org.springframework.data.web.PageableHandlerMethodArgumentResolver")##
$output.require("org.springframework.http.MediaType")##
$output.require("org.springframework.test.web.servlet.MockMvc")##
$output.require("org.springframework.test.web.servlet.setup.MockMvcBuilders")##

$output.require("com.jaxio.demo.domain.Book")##
$output.require("com.jaxio.demo.repository.BookRepository")##
$output.require("com.jaxio.demo.rest.BookResource")##
$output.require("com.jaxio.demo.rest.BookResourceUnitTest")##
$output.require("com.jaxio.demo.searchrepository.BookSearchRepository")##
$output.require("com.jaxio.demo.utils.JsonUtils")##

/**
 * Unit tests for class BookRepository.
 * 
 */
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

