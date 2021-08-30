package pl.rafalmiskiewicz.ADOZL.admin;

import java.util.HashMap;
import java.util.Map;

public class Note {

    public String getSubject() {
        return "RM Tytuł";
    }

    public String getContent() {
        return "RM ciało";
    }

    public Map<String, String> getData() {
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("test","test");
        return data;
    }
}
