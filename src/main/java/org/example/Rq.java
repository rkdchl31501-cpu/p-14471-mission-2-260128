package org.example;

import java.util.HashMap;
import java.util.Map;

public class Rq {

    private String action;
    private Map<String, String> paramMap;

    public Rq(String cmd) {
        paramMap = new HashMap<>();

        String[] cmdBits = cmd.split("\\?");
        action = cmdBits[0];

        String params = cmdBits.length > 1 ? cmdBits[1] : "";
        String[] paramsBits = params.split("&");

        for (String param : paramsBits) {
            if (param.isEmpty()) continue;

            String[] paramBits = param.split("=");
            String key = paramBits[0];
            String value = paramBits.length > 1 ? paramBits[1] : "";

            paramMap.put(key, value);
        }
    }

    public String getAction() {
        return action;
    }

    public String getParam(String key) {
        return paramMap.get(key);
    }

    public String getParam(String key, String defaultValue) {
        if (paramMap.containsKey(key)) {
            return paramMap.get(key);
        }
        return defaultValue;
    }
    public int getParamAsInt(String key) {
        return Integer.parseInt(paramMap.get(key));
    }

    public int getParamAsInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(paramMap.get(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
