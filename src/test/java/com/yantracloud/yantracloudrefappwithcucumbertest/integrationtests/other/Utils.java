package com.yantracloud.yantracloudrefappwithcucumbertest.integrationtests.other;

import com.yantracloud.yantracloudrefappwithcucumbertest.pojo.Auth0ClientCredentialFlowRequest;
import com.yantracloud.yantracloudrefappwithcucumbertest.pojo.Auth0ClientCredentialFlowResponse;
import org.assertj.core.api.Assertions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Utils {

    public static String getToken(String tokenUrl, String clientID, String clientSecret, String audience){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Auth0ClientCredentialFlowRequest> request = new HttpEntity<>(new Auth0ClientCredentialFlowRequest(clientID,clientSecret,audience,"client_credentials"));
        ResponseEntity<Auth0ClientCredentialFlowResponse> responseEntity = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Auth0ClientCredentialFlowResponse.class);
        Assertions.assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        Auth0ClientCredentialFlowResponse auth0ClientCredentialFlowResponse = responseEntity.getBody();
        return auth0ClientCredentialFlowResponse.getAccess_token();
    }
}
