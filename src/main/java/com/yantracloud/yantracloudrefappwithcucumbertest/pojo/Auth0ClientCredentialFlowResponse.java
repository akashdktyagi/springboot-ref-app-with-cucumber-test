package com.yantracloud.yantracloudrefappwithcucumbertest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth0ClientCredentialFlowResponse {
    String access_token;
    String scope;
    String expires_in;
    String token_type;
}
