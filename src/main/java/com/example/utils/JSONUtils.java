package com.example.utils;

/**
 * Created by hucheng on 2017/5/1.
 */
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class JSONUtils {

    private boolean compress;
    private final int INDENTFACTOR = 4;
    private final String ENCODING = "UTF-8";

    public JSONUtils() {
        compress = true;
    }

    public JSONUtils(boolean compress) {
        this.compress = true;
        this.compress = compress;
    }

    public String toJSON(String root, Object object) throws Exception {
        JSONObject jsonObj = getJSON(root, object);
        return toJSON(jsonObj);
    }

    public String toJSON(JSONObject jsonObj) throws Exception {
        if (jsonObj == null)
            return "";
        int indentFactor = 2;
        if (compress)
            indentFactor = 0;
        return jsonObj.toString(indentFactor).trim();
    }

    public boolean toJSON(String root, Object object, String filename)
            throws Exception {
        JSONObject jsonObj = getJSON(root, object);
        return toJSON(jsonObj, filename);
    }

    public JSONObject getJSON(String root, Object object) throws Exception {
        if (root == null || root.trim().length() == 0)
            throw new Exception("root error");
        if (object == null) {
            return null;
        } else {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put(root, object);
            return jsonObj;
        }
    }

    public boolean toJSON(JSONObject jsonObj, String filename)
            throws Exception
    {
        File file;
        Writer writer;
        if(jsonObj == null)
            return false;
        file = new File(filename);
        if(!file.getParentFile().exists() && !file.getParentFile().mkdirs())
            return false;
        if(file.exists() && !file.delete())
            return false;
        writer = null;
        try
        {
            writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            jsonObj.write(writer);
        }
        catch(JSONException e)
        {
            throw new JSONException((new StringBuilder()).append(e.getMessage()).toString());
        }
        writer.close();
        return true;
    }

    public static JSONObject string2JSON(String str, String split)
            throws JSONException {
        JSONObject json = new JSONObject();
        String arrStr[] = str.split(split);
        for (int i = 0; i < arrStr.length; i++) {
            String arrKeyValue[] = arrStr[i].split("=");
            json.put(arrKeyValue[0],
                    arrStr[i].substring(arrKeyValue[0].length() + 1));
        }

        return json;
    }

}

