package utilities;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DataDriven {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String,Object> readJson(String filePath){
        File file= new File(filePath);
        return mapper.readValue(file,
                new TypeReference<Map<String, Object>>() {
                });

    }

}
