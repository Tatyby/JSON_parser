package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        list.forEach(System.out::println);
    }

    public static String readString(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                stringBuilder.append(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray array = (JSONArray) parser.parse(json);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for (Object employee : array) {
                list.add(gson.fromJson(String.valueOf(employee), Employee.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}