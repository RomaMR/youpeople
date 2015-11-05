package com.optigra.youpeople.web.controller;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by romanmudryi on 10.08.15.
 */
public class PersonControllerTest {

    private RestTemplate restTemplate;

    private HttpEntity<String> entity;

    private HttpHeaders headers;

    private final static String URL = "http://localhost:8080/youpeople/api/persons/search?organizationName=";

    private final static String LOGIN = "admin@gmail.com";

    private final static String PASSWORD = "password";

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String authString = LOGIN + ":" + PASSWORD;
        String base64 = new String(Base64.encodeBase64(authString.getBytes()));
        headers.set("Authorization", "Basic " + base64);
        entity = new HttpEntity<String>("parameters", headers);
    }

//    @Test(timeout = 500)
//    public void testGetById() throws Exception {
//        ResponseEntity<Void> result = restTemplate.exchange(URL + "optigra", HttpMethod.GET, entity, Void.class);
//    }
//
//    @Test(timeout = 500)
//    public void testGetById2() throws Exception {
//        ResponseEntity<Void> result = restTemplate.exchange(URL + "elex", HttpMethod.GET, entity, Void.class);
//    }

    @Test
    public void testGetByOrganizationId() throws Exception {

    }

    @Test
    public void testSearchByOrganizationId() throws Exception {

    }
}