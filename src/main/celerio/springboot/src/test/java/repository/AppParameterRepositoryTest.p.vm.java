$output.javaTest("${configuration.rootPackage}.repository", "AppParameterRepositoryTest")##

$output.require("static org.assertj.core.api.Assertions.assertThat")##

$output.require("org.junit.Test")##
$output.require("org.junit.runner.RunWith")##
$output.require("org.slf4j.Logger")##
$output.require("org.slf4j.LoggerFactory")##
$output.require("org.springframework.beans.factory.annotation.Autowired")##
$output.require("org.springframework.boot.autoconfigure.domain.EntityScan")##
$output.require("org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest")##
$output.require("org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager")##
$output.require("org.springframework.test.context.ActiveProfiles")##
$output.require("org.springframework.test.context.junit4.SpringRunner")##

$output.require("com.jaxio.demo.domain.AppParameter")##
$output.require("com.jaxio.demo.repository.AppParameterRepository")##
$output.require("com.jaxio.demo.rest.AppParameterResource")##

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles="TEST")
@EntityScan("${configuration.rootPackage}.domain")
public class AppParameterRepositoryTest {

	private final Logger log = LoggerFactory.getLogger(AppParameterResource.class);
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private AppParameterRepository repository;
	
    @Test
    public void should_find_no_AppParameter_if_repository_is_empty() {
        Iterable<AppParameter> appParameters = repository.findAll();
 
        log.info("appParameters: " + appParameters);
        
        assertThat(appParameters).isEmpty();
    }
    
    @Test
    public void should_store_a_AppParameter() {
    	AppParameter param = new AppParameter();
        param.setDomain("UNITTEST");
        param.setKey("KEY");
        param.setValue("VALUE");
        
    	AppParameter appParameter = repository.save(param);
 
        assertThat(appParameter).hasFieldOrPropertyWithValue("domain", "UNITTEST");
        assertThat(appParameter).hasFieldOrPropertyWithValue("key", "KEY");
    }
 
    @Test
    public void should_delete_all_AppParameters() {
    	AppParameter param1 = new AppParameter();
        param1.setDomain("UNITTEST");
        param1.setKey("KEY_1");
        param1.setValue("VALUE_1");
        
        entityManager.persist(param1);
        
        AppParameter param2 = new AppParameter();
        param2.setDomain("UNITTEST");
        param2.setKey("KEY_2");
        param2.setValue("VALUE_2");
        
        entityManager.persist(param2);
 
        repository.deleteAll();
 
        assertThat(repository.findAll()).isEmpty();
    }
 
    @Test
    public void should_find_all_AppParameters() {
        AppParameter param1 = new AppParameter();
        param1.setDomain("UNITTEST");
        param1.setKey("KEY_1");
        param1.setValue("VALUE_1");
        
        entityManager.persist(param1);

 
        AppParameter param2 = new AppParameter();
        param2.setDomain("UNITTEST");
        param2.setKey("KEY_2");
        param2.setValue("VALUE_2");
        
        entityManager.persist(param2);
        
        AppParameter param3 = new AppParameter();
        param3.setDomain("UNITTEST");
        param3.setKey("KEY_3");
        param3.setValue("VALUE_3");
        
        entityManager.persist(param3);
 
        Iterable<AppParameter> appParameters = repository.findAll();
 
        assertThat(appParameters).hasSize(3).contains(param1, param2, param3);
    }
 
    @Test
    public void should_find_AppParameter_by_id() {
    	AppParameter param = new AppParameter();
        param.setDomain("UNITTEST");
        param.setKey("KEY");
        param.setValue("VALUE");
        
        entityManager.persist(param);
 
        AppParameter foundAppParameter = repository.findOne(param.getId());
 
        assertThat(foundAppParameter).isEqualTo(param);
    }
}
