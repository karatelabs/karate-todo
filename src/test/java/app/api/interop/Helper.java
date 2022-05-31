package app.api.interop;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static Map<String, Object> doWork(String arg) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", arg);
        return map;
    }

}
