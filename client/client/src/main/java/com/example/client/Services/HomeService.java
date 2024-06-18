package com.example.client.Services;

import com.example.client.entities.ShoppingList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HomeService {

    public List<ShoppingList> fetchYourLists() throws Exception {
        URL url = new URI("http://localhost:8080/list/").toURL();
        HttpURLConnection conn = getHttpURLConnection(url);
        conn.setRequestMethod("GET");


        try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // Check if the response is empty
            if (response.length() == 0) {
                return new ArrayList<>(); // Return an empty list
            }

            // Parse the response
            JSONArray jsonArray = new JSONArray(response.toString());
            List<ShoppingList> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                list.add(new ShoppingList(jsonObject.getString("shoppingListName"), jsonObject.getInt("id")));
            }

            return list;
        }
    }

    public boolean deleteList(Integer id) throws Exception {
        URL url = new URI("http://localhost:8080/list/" + id).toURL();
        HttpURLConnection conn = getHttpURLConnection(url);

        conn.setRequestMethod("DELETE");

        int responseCode = conn.getResponseCode();

        return responseCode == HttpURLConnection.HTTP_OK;
    }

//
//    public List<Object> fetchSharedLists() throws Exception {
//        URL url = new URI("http://api.example.com/sharedlists").toURL();
//        HttpURLConnection conn = getHttpURLConnection(url);
//
//        try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
//            StringBuilder response = new StringBuilder();
//            String responseLine;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//
//            // Parse the response
//            JSONArray jsonArray = new JSONArray(response.toString());
//            List<Object> list = new ArrayList<>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                list.add(jsonObject);
//            }
//
//            return list;
//        }
//    }

    private HttpURLConnection getHttpURLConnection(URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");

        // Get the token from the TokenHolderService
        String token = TokenHolderService.getInstance().getToken();

        // Include the token in the Authorization header
        conn.setRequestProperty("Authorization", "Bearer " + token);

        return conn;
    }
}