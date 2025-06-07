package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("name", "Alice");
        user.put("age", 30);
        user.put("active", true);

        SmileFactory smileFactory = new SmileFactory();
        ObjectMapper jsonMapper = new ObjectMapper();
        ObjectMapper smileMapper = new ObjectMapper(smileFactory);
        byte[] jsonBytes = jsonMapper.writeValueAsBytes(user);

        try (FileOutputStream out = new FileOutputStream("userJson.dat")) {
            out.write(jsonBytes);
        }

        File outputFile = new File("user.smile");

        smileMapper.writeValue(outputFile, user);

        System.out.println("Smile file written to: " + outputFile.getAbsolutePath());
    }
}