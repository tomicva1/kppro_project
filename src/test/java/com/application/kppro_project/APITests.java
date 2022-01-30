package com.application.kppro_project;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.concurrent.Flow;

@SpringBootTest()
@AutoConfigureMockMvc
public class APITests {

    @Autowired MockMvc mvc;

    private HttpRequest.BodyPublisher bodyPublisher = new HttpRequest.BodyPublisher() {
        @Override
        public long contentLength() {
            return 0;
        }

        @Override
        public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {

        }
    };

    @Test
    void userTest() throws Exception {
        //Auth normal user (employee)
        String token = authUser("user1", "Password1");

        // Core operations provided by Spring Data REST
        //Employees
        this.mvc.perform(get("/employees").header("Authorization", "Bearer " + token)) //
                .andDo(print())//
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        this.mvc.perform(get("/employees/1").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaTypes.HAL_JSON)) //
                .andExpect(content().string("{\"data\":{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Nowak\",\"role\":\"USER\",\"username\":\"user1\",\"departmentId\":1,\"email\":\"test@seznam.cz\",\"mobile\":\"123456789\",\"name\":\"John Nowak\",\"links\":[{\"rel\":\"employees\",\"href\":\"http://localhost/employees\"},{\"rel\":\"self\",\"href\":\"http://localhost/employees\"}]}}"));

        //Vacations

        this.mvc.perform(get("/vacations").header("Authorization", "Bearer " + token)).andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        this.mvc.perform(get("/vacations/emp").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
                .andExpect(content().string("{\"data\":[{\"id\":1,\"employeeId\":1,\"dateFrom\":\"2022-01-25T23:00:00.000+00:00\",\"dateTo\":\"2022-01-30T23:00:00.000+00:00\",\"note\":\"First vacation\",\"status\":\"APPROVED\",\"updateTime\":\"2022-01-30T23:00:00.000+00:00\",\"updatedBy\":6,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/vacations/1\"},{\"rel\":\"vacation\",\"href\":\"http://localhost/vacations/emp\"}]},{\"id\":2,\"employeeId\":1,\"dateFrom\":\"2022-01-25T23:00:00.000+00:00\",\"dateTo\":\"2022-01-30T23:00:00.000+00:00\",\"note\":\"Second vacation\",\"status\":\"WAITING\",\"updateTime\":null,\"updatedBy\":null,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/vacations/2\"},{\"rel\":\"vacation\",\"href\":\"http://localhost/vacations/emp\"}]},{\"id\":3,\"employeeId\":1,\"dateFrom\":\"2022-01-25T23:00:00.000+00:00\",\"dateTo\":\"2022-01-30T23:00:00.000+00:00\",\"note\":\"Third vacation\",\"status\":\"WAITING\",\"updateTime\":null,\"updatedBy\":null,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/vacations/3\"},{\"rel\":\"vacation\",\"href\":\"http://localhost/vacations/emp\"}]},{\"id\":4,\"employeeId\":1,\"dateFrom\":\"2022-01-25T23:00:00.000+00:00\",\"dateTo\":\"2022-01-30T23:00:00.000+00:00\",\"note\":\"Fouth vacation\",\"status\":\"WAITING\",\"updateTime\":null,\"updatedBy\":null,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/vacations/4\"},{\"rel\":\"vacation\",\"href\":\"http://localhost/vacations/emp\"}]},{\"id\":5,\"employeeId\":1,\"dateFrom\":\"2022-01-25T23:00:00.000+00:00\",\"dateTo\":\"2022-01-30T23:00:00.000+00:00\",\"note\":\"Fifth vacation\",\"status\":\"WAITING\",\"updateTime\":null,\"updatedBy\":null,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/vacations/5\"},{\"rel\":\"vacation\",\"href\":\"http://localhost/vacations/emp\"}]},{\"id\":6,\"employeeId\":1,\"dateFrom\":\"2022-01-25T23:00:00.000+00:00\",\"dateTo\":\"2022-01-30T23:00:00.000+00:00\",\"note\":\"Sixth vacation\",\"status\":\"WAITING\",\"updateTime\":null,\"updatedBy\":null,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/vacations/6\"},{\"rel\":\"vacation\",\"href\":\"http://localhost/vacations/emp\"}]},{\"id\":7,\"employeeId\":1,\"dateFrom\":\"2022-01-25T23:00:00.000+00:00\",\"dateTo\":\"2022-01-30T23:00:00.000+00:00\",\"note\":\"Seventh vacation\",\"status\":\"WAITING\",\"updateTime\":null,\"updatedBy\":null,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/vacations/7\"},{\"rel\":\"vacation\",\"href\":\"http://localhost/vacations/emp\"}]}]}"));

        this.mvc.perform(get("/vacations/1").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaTypes.HAL_JSON)) //
                .andExpect(content().string("{\"data\":{\"id\":1,\"employeeId\":1,\"dateFrom\":\"2022-01-25T23:00:00.000+00:00\",\"dateTo\":\"2022-01-30T23:00:00.000+00:00\",\"note\":\"First vacation\",\"status\":\"APPROVED\",\"updateTime\":\"2022-01-30T23:00:00.000+00:00\",\"updatedBy\":6,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/vacations/1\"},{\"rel\":\"vacation\",\"href\":\"http://localhost/vacations/emp\"}]}}"));

        /*this.mvc.perform(post("/vacations/1").header("Authorization", "Bearer " + token).content("{\"id\":5,\"employeeId\":1,\"dateFrom\":\"2023-01-28T23:00:00.000Z\",\"dateTo\":\"2023-01-30T23:00:00.000Z\",\"note\":\"Fifth vacation5\",\"status\":\"WAITING\",\"updateTime\":null,\"updatedBy\":null,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/vacations/5\"},{\"rel\":\"vacation\",\"href\":\"http://localhost:8080/vacations/emp\"}]}")) //
                .andDo(print()) //
                .andExpect(status().is4xxClientError()); //*/

        this.mvc.perform(delete("/vacations/1").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().is4xxClientError());

        //Feedbacks

        this.mvc.perform(get("/feedbacks").header("Authorization", "Bearer " + token)).andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        this.mvc.perform(get("/feedbacks/emp").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
                .andExpect(content().string("{\"data\":[{\"id\":1,\"quality\":5,\"note\":\"Work morale is scarce\",\"author\":6,\"creationTime\":\"2022-01-24T23:00:00.000+00:00\",\"employeeId\":1,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/feedbacks/1\"},{\"rel\":\"feedback\",\"href\":\"http://localhost/feedbacks/emp\"},{\"rel\":\"feedback\",\"href\":\"http://localhost/feedbacks\"}]},{\"id\":5,\"quality\":5,\"note\":\"Very below average performance\",\"author\":6,\"creationTime\":\"2022-01-24T23:00:00.000+00:00\",\"employeeId\":1,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/feedbacks/5\"},{\"rel\":\"feedback\",\"href\":\"http://localhost/feedbacks/emp\"},{\"rel\":\"feedback\",\"href\":\"http://localhost/feedbacks\"}]}]}"));

        this.mvc.perform(get("/feedbacks/1").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaTypes.HAL_JSON)) //
                .andExpect(content().string("{\"data\":{\"id\":1,\"quality\":5,\"note\":\"Work morale is scarce\",\"author\":6,\"creationTime\":\"2022-01-24T23:00:00.000+00:00\",\"employeeId\":1,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/feedbacks/1\"},{\"rel\":\"feedback\",\"href\":\"http://localhost/feedbacks/emp\"},{\"rel\":\"feedback\",\"href\":\"http://localhost/feedbacks\"}]}}"));

        /*this.mvc.perform(post("/vacations/1").header("Authorization", "Bearer " + token).content("{\"id\":5,\"employeeId\":1,\"dateFrom\":\"2023-01-28T23:00:00.000Z\",\"dateTo\":\"2023-01-30T23:00:00.000Z\",\"note\":\"Fifth vacation5\",\"status\":\"WAITING\",\"updateTime\":null,\"updatedBy\":null,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/vacations/5\"},{\"rel\":\"vacation\",\"href\":\"http://localhost:8080/vacations/emp\"}]}")) //
                .andDo(print()) //
                .andExpect(status().is4xxClientError()); //*/

        this.mvc.perform(delete("/feedbacks/1").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().is4xxClientError());

        this.mvc.perform(get("/departments").header("Authorization", "Bearer " + token)).andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void managerTest() throws Exception {
        //Auth normal user (employee)
        String token = authUser("manager1", "Password1");

        // Core operations provided by Spring Data REST
        //Employees
        this.mvc.perform(get("/employees").header("Authorization", "Bearer " + token)) //
                .andDo(print())//
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        this.mvc.perform(get("/employees/1").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaTypes.HAL_JSON)) //
                .andExpect(content().string("{\"data\":{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Nowak\",\"role\":\"USER\",\"username\":\"user1\",\"departmentId\":1,\"email\":\"test@seznam.cz\",\"mobile\":\"123456789\",\"name\":\"John Nowak\",\"links\":[{\"rel\":\"employees\",\"href\":\"http://localhost/employees\"},{\"rel\":\"self\",\"href\":\"http://localhost/employees\"}]}}"));

        //Vacations

        this.mvc.perform(get("/vacations").header("Authorization", "Bearer " + token)).andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        this.mvc.perform(get("/vacations/emp").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
                .andExpect(content().string("{\"data\":[]}"));

        this.mvc.perform(get("/vacations/1").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaTypes.HAL_JSON)) //
                .andExpect(content().string("{\"data\":{\"id\":1,\"employeeId\":1,\"dateFrom\":\"2022-01-25T23:00:00.000+00:00\",\"dateTo\":\"2022-01-30T23:00:00.000+00:00\",\"note\":\"First vacation\",\"status\":\"APPROVED\",\"updateTime\":\"2022-01-30T23:00:00.000+00:00\",\"updatedBy\":6,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/vacations/1\"},{\"rel\":\"vacation\",\"href\":\"http://localhost/vacations/emp\"}]}}"));

        this.mvc.perform(delete("/vacations/1").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk());

        //Feedbacks

        this.mvc.perform(get("/feedbacks").header("Authorization", "Bearer " + token)).andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        this.mvc.perform(get("/feedbacks/emp").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
                .andExpect(content().string("{\"data\":[]}"));

        this.mvc.perform(get("/feedbacks/1").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaTypes.HAL_JSON)) //
                .andExpect(content().string("{\"data\":{\"id\":1,\"quality\":5,\"note\":\"Work morale is scarce\",\"author\":6,\"creationTime\":\"2022-01-24T23:00:00.000+00:00\",\"employeeId\":1,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/feedbacks/1\"},{\"rel\":\"feedback\",\"href\":\"http://localhost/feedbacks/emp\"},{\"rel\":\"feedback\",\"href\":\"http://localhost/feedbacks\"}]}}"));

        /*this.mvc.perform(post("/vacations/1").header("Authorization", "Bearer " + token).content("{\"id\":5,\"employeeId\":1,\"dateFrom\":\"2023-01-28T23:00:00.000Z\",\"dateTo\":\"2023-01-30T23:00:00.000Z\",\"note\":\"Fifth vacation5\",\"status\":\"WAITING\",\"updateTime\":null,\"updatedBy\":null,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/vacations/5\"},{\"rel\":\"vacation\",\"href\":\"http://localhost:8080/vacations/emp\"}]}")) //
                .andDo(print()) //
                .andExpect(status().is4xxClientError()); //*/

        this.mvc.perform(delete("/feedbacks/1").header("Authorization", "Bearer " + token)) //
                .andDo(print()) //
                .andExpect(status().isOk());

        this.mvc.perform(get("/departments").header("Authorization", "Bearer " + token)).andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private String authUser(String username, String password) throws Exception{
        // Customer ID
        final String customerKey = username;
        // Customer secret
        final String customerSecret = password;

        // Concatenate customer key and customer secret and use base64 to encode the concatenated string
        String plainCredentials = customerKey + ":" + customerSecret;
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        // Create authorization header
        String authorizationHeader = "Basic " + base64Credentials;

        HttpClient client = HttpClient.newHttpClient();

        // Create HTTP request object
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/login"))
                .POST(bodyPublisher)
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .build();
        // Send HTTP request
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());
        return jsonObject.getString("token");
    }

}
