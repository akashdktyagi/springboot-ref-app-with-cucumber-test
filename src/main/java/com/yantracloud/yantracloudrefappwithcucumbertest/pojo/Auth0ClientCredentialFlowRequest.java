package com.yantracloud.yantracloudrefappwithcucumbertest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth0ClientCredentialFlowRequest {
    String client_id;
    String client_secret;
    String audience;
    String grant_type;
}
