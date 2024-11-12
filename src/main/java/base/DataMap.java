package base;

import java.util.HashMap;
import java.util.Map;

public class DataMap {

    public static ThreadLocal<Map<String, Object>> dataMap = ThreadLocal.withInitial(HashMap::new);
}
