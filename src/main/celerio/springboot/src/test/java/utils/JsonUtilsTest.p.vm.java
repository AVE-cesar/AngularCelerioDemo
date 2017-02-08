$output.javaTest("${configuration.rootPackage}.utils", "JsonUtilsTest")##

$output.require("static org.junit.Assert.assertEquals")##
$output.require("static org.junit.Assert.fail")##

$output.require("java.io.IOException")##
$output.require("java.io.Serializable")##

$output.require("org.junit.Test")##

$output.require("com.jaxio.demo.utils.JsonUtils")##
$output.require("com.jaxio.demo.utils.TestObject")##

public final class JsonUtilsTest {

	@Test
	public void testConvertObjectToJsonBytes() {
	
		TestObject object = new TestObject();
		object.setId("1");
		object.setTitle("test");
		
		try {
			String result = new String(JsonUtils.convertObjectToJsonBytes(object));
			
			assertEquals(result, "{\"title\":\"test\",\"id\":\"1\"}");
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

}

class TestObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3155799279999651919L;
	
	private String title;
	
	private String id;

	public TestObject() {
		super();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}