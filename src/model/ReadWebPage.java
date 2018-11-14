package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ReadWebPage {

    public static void main(String[] args) throws MalformedURLException, IOException {

        BufferedReader br = null;

        try {
            String theURL = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3 "; //this can point to any URL;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
            System.out.println(sb.toString());

            parseFood(sb.toString());
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    public static void parseFood(String webContent){
        JSONObject obj = new JSONObject(webContent);
        JSONArray recipes = obj.getJSONArray("results");
        System.out.println(recipes);
        for (int i=0; i < recipes.length(); i++) {
            JSONObject recipe = recipes.getJSONObject(i);
            String ingredients = recipe.getString("ingredients");
            System.out.println(ingredients);
//            ArrayList<String> items = splitOnComma(ingredients);
        }
    }

//    private static ArrayList<String> splitOnComma(String line) {
//        String[] splits = line.split(",");
//        return new ArrayList<>(Arrays.asList(splits));
//    }
}

