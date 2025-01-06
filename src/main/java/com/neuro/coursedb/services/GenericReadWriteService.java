package com.neuro.coursedb.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuro.coursedb.models.FilePath;

import java.io.*;
import java.util.List;

public class GenericReadWriteService {

    public <T> List<T> getData(Class<T> type){
        return jsonToObject(readFile(getFilePath(type)), type);
    }

    public <T, G> void writeData(List<T> object, Class<G> type){
        writeFile(getFilePath(type), objectToJson(object));
    }

    private <T> List<T> jsonToObject(String json, Class<T> clazz){
        try {
            return new ObjectMapper().readValue(json, new TypeReference<List<T>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> String objectToJson(List<T> list){
        try {
            return new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void writeFile(String filePath, String json){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> String getFilePath(Class<T> clazz){
        if (clazz.isAnnotationPresent(FilePath.class)){
            FilePath filePath = clazz.getAnnotation(FilePath.class);
            return filePath.value();
        }
        return null;
    }
}
