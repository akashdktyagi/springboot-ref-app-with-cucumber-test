package com.yantracloud.yantracloudrefappwithcucumbertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YantracloudRefAppWithCucumberTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(YantracloudRefAppWithCucumberTestApplication.class, args);
	}

}

/* Command to get the Token
curl --request POST \
  --url https://dev-j8ajv706.us.auth0.com/oauth/token \
  --header 'content-type: application/json' \
  --data '{"client_id":"L8XRJjXgjJqsrUCcELtVb5m46KqWgLlf","client_secret":"5PglHF_jc5v-81fzlJcOTM2YZ2iABNYAuekfO_WR3W4IHKN0-7yxPqcbdHO0LI65","audience":"http://localhost:9096/api","grant_type":"client_credentials"}'
 */