package com.barnard.service;

import com.barnard.model.EmailParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@Service
public class EmailService {

    @Value("${email.service.base-url}")
    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();

    public void sendEmail(EmailParams email) {
        restTemplate.postForLocation(BASE_URL, email);
    }

}
