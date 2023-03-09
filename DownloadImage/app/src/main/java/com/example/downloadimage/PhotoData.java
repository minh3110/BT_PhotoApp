package com.example.downloadimage;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PhotoData {
    public static ArrayList<Photo> GetData(Context context, String fileName)
    {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ArrayList<Photo> dataItems = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Photo item = new Photo(
                        Integer.parseInt(jsonObject.getString("Id")),
                        jsonObject.getString("Source"),
                        jsonObject.getString("Title"),
                        jsonObject.getString("Content")
                );
                dataItems.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataItems;
    }
    public static Photo getPhotoById(ArrayList<Photo> data, int id)
    {
        for(int i = 0; i < data.size(); i++)
        {
            if(data.get(i).getId() == id)
                return data.get(i);
        }
        return null;
    }
}
