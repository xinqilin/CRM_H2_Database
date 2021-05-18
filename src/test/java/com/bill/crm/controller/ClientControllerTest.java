package com.bill.crm.controller;

import com.bill.crm.vo.request.ClientRequestVo;
import com.bill.crm.vo.response.CodeMessageVo;
import com.bill.crm.vo.response.ResponseOneVo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    void selectOne_superuser() throws Exception {


    }

    @Test
    void addOne_paulByOperator() throws Exception {

        ClientRequestVo clientRequestVo = ClientRequestVo.builder()
                .companyId(1L)
                .name("paul")
                .email("paul@gmail.com")
                .phone("0987654321")
                .build();

        HttpHeaders headers = createHttpHeaders("OPERATOR", "bill");

        HttpEntity<ClientRequestVo> request = new HttpEntity<>(clientRequestVo, headers);

        ResponseEntity<ResponseOneVo> response =
                testRestTemplate.postForEntity("/client/add", request, ResponseOneVo.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());

        ResponseOneVo resOneDto = response.getBody();
        Assertions.assertEquals(CodeMessageVo.SUCCESS.getCode(), resOneDto.getCode());
        Assertions.assertEquals(CodeMessageVo.SUCCESS.getMessage(), resOneDto.getMessage());

        int id = (int) resOneDto.getData();
        Assertions.assertTrue(id > 0);
    }

    private HttpHeaders createHttpHeaders(String username, String password) {
        String basicCredentials = username + ":" + password;
        byte[] base64Bytes = Base64.encodeBase64(basicCredentials.getBytes());
        String basicBase64EncodeCredentials = new String(base64Bytes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + basicBase64EncodeCredentials);
        return headers;
    }

}
