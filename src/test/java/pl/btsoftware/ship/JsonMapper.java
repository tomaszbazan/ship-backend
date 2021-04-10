package pl.btsoftware.ship;

import com.jayway.jsonpath.JsonPath;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {
    public static String asString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String readPath(String response, String path) {
        return JsonPath.read(response, path).toString();
    }
}
