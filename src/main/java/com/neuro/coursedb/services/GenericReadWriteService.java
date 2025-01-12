package com.neuro.coursedb.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuro.coursedb.errors.NoJsonDataException;
import com.neuro.coursedb.models.FilePath;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
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
            throw new NoJsonDataException(e.getMessage());
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
        if (!Files.exists(Path.of(filePath))){
            createFile(filePath);
        }
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

    private void createFile(String filePath){
        try {
            Files.createFile(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFile(String filePath, String json){
        if (!Files.exists(Path.of(filePath))){
            createFile(filePath);
        }
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
