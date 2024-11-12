package utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class JSONUtil {
    public static Map<String, Object> getDataFromJson(String path){
        FileReader fileReader;
        JSONObject jsonObject;
        Map<String, Object> dataMap = new HashMap<>();
        try {
            fileReader = new FileReader(path);
            jsonObject = new JSONObject(new JSONTokener(fileReader));
            dataMap = jsonObject.toMap();
        } catch (FileNotFoundException ignored) {
        }
        return dataMap;
    }
}
