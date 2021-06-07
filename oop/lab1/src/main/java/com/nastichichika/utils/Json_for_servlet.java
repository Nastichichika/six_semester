package com.nastichichika.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Json_for_servlet {

    public static <T> void makeJsonAnswer(T obj, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(new ObjectMapper().writeValueAsString(obj));
        out.flush();
    }

    public static JSONObject jsonBodyFromRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jsonBody = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
        } catch (Exception e) {
            response.sendError(400, "Bad request");
        }
        return new JSONObject(jsonBody.toString());
    }
}
