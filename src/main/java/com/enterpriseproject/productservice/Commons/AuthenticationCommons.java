package com.enterpriseproject.productservice.Commons;

import com.enterpriseproject.productservice.DTOs.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommons {

    @Value("${VALIDATE_USER_URL}")
    private String userValidateUrl;
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        ResponseEntity<UserDto> userDtoResponse = restTemplate.postForEntity(
                userValidateUrl + token,
                null,
                UserDto.class
        );

        if(userDtoResponse.getBody() == null) return null;

        return userDtoResponse.getBody();
    }
}
