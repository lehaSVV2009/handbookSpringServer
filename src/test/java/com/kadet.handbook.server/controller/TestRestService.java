package com.kadet.handbook.server.controller;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class TestRestService extends BaseWebApplicationContextTests {

    @Test
    public void emptyTest () {

    }

   /* @Test
    public void testGetBookAsXML() throws Exception {
        request.setMethod("GET");
        request.addHeader("Accept", "application/xml");
        request.addHeader("Content-Type", "application/xml");
        request.setRequestURI("http://localhost:8080/handbookSpringServer-1.0.0-SNAPSHOT-D/publisher/chapters");
        request.setContentType("application/xml");
        request.setMethod("GET");

        servlet.service(request, response);
        String result = response.getContentAsString();
        Assert.assertEquals(200, response.getStatus());
        String expectedXML = "<chapter><id>2</id><text>text2</text><title>title1</title></chapter>";
        Assert.assertEquals(expectedXML, result);
    }*/
/*
    @Test
    public void testGetBookAsJSon() throws Exception {
        request.setMethod("GET");
        request.addHeader("Accept", "application/json");
//        request.addHeader("Content-Type", "application/xml");
        request.addHeader("Content-Type", "application/json");
        request.setRequestURI("publisher/chapter/2");
//        request.setContentType("application/xml");
        request.setContentType("application/json");
        request.setMethod("GET");

        servlet.service(request, response);
        String result = response.getContentAsString();
        System.out.println(response.getContentAsString());
        Assert.assertEquals(200, response.getStatus());
        String expectedJSON = "{\"id\":\"2\",\"text\":\"text2\",\"title\":title2}";
        Assert.assertEquals(createTree(expectedJSON), createTree(result));
    }

    private JsonNode createTree(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, JsonNode.class);
    }*/

}
