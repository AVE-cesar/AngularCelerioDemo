$output.javaTest("${configuration.rootPackage}.utils", "JsonUtils")##

$output.require("java.io.IOException")##
$output.require("com.fasterxml.jackson.annotation.JsonInclude")##
$output.require("com.fasterxml.jackson.databind.ObjectMapper")##

/**
 * Contains various util methods around JSON.
 * 
 * @author bertrand
 *
 */
public final class JsonUtils {

	/**
	 * Converts an object into its JSON representation as an array of bytes. 
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 */
    public final static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
