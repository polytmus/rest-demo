package me.qebs.restdemo;

import me.qebs.restdemo.config.CustomRestTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestDemoApplicationTests {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private CustomRestTemplate customRestTempate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetWithBody() {
        List<MediaType> acceptableMediaTypes = new ArrayList<>(1);
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);

        String body = "hello world";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);


        ResponseEntity<String> result = customRestTempate.exchange("http://localhost:8080/test/get",
                HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }

    @Test
    public void testGet() {
        Map<String, Object> uriVariables = new HashMap<>(1);
        uriVariables.put("id", 2);

        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/test/id/1",
                HttpMethod.GET, null, String.class);
        System.out.println(result);
    }

}
