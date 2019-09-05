package com.card.collector.backend.service;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacebookService {

    Logger logger = LoggerFactory.getLogger(FacebookService.class);

    private final String appId = "1133368790178057";
    private final String appSecret = "4b6a5d2ae6e67e2c123c3b0b72692cef";
    private final String pipeChar = "%7C";

    private final String faceBookApi = "https://graph.facebook.com/";

    public boolean validateToken(String token){
        try {
            String restUrl = faceBookApi + "debug_token?input_token=" + token + "&access_token=" + appId + pipeChar + appSecret;
            ResponseEntity<String> response= queryRestApi(restUrl);

            if (response != null && response.getStatusCodeValue() == 200) {
                JSONObject responseJsonObject = new JSONObject(response.getBody());
                JSONObject data = responseJsonObject.getJSONObject("data");
                String applicationId = data.getString("app_id");
                Boolean isValid = data.getBoolean("is_valid");
                if(isValid ==true && applicationId.equals(appId)){
                    logger.info("Facebook token validated. userId=" + data.getString("user_id"));
                    return true;
                }
            }
        } catch (Exception e){
            logger.error("Facebook token validation failed:", e);
        }

        return false;
    }


    public JSONObject getUserInfo(String token){
        try {
            String restUrl = faceBookApi + "me?fields=id,name,email&access_token=" + token;
            ResponseEntity<String> response= queryRestApi(restUrl);

            if (response != null && response.getStatusCodeValue() == 200) {
                JSONObject responseJsonObject = new JSONObject(response.getBody());
                if(responseJsonObject.get("email") != null) {
                    String email = responseJsonObject.getString("email");
                    String fullName = responseJsonObject.getString("name");
                    logger.info("Facebook email and fullname retrieved via graph api. fullname=" + fullName + ",email="+email);
                    return responseJsonObject;
                }
            }
        } catch (Exception e){
            logger.error("Facebook userinfo failed:", e);
        }

        return null;
    }

    private ResponseEntity<String> queryRestApi(String restUrl){
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

        HttpComponentsClientHttpRequestFactory requestFactory= new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);

        return new RestTemplate(requestFactory).exchange(restUrl, HttpMethod.GET, null, String.class);
    }

    /*
    public static void main(String args[]){

        FacebookService f = new FacebookService();
        String testToken = "EAAQGyvwjWQkBAHgS7gxFliWJxVpzzZCwS2bRuS0GV56bodyEdezx8s1IZBt4zJoUaRkXvon6gJNR73tSg8luZC94YIlbPAfZC7YbzjeSHOZBTJoZAgzvnphqRm8lulZAgs6z2cWzOIL3CMn4iyJ6ZA6ZBVZBg7WoAva6nYc5qHzHM5AszeMup8A4iNVrwpjdTILHmPz54pxlUqhYlzKM4MmlgiQrZAO0H8oOrXC2yVZByVc0jgZDZD";

        f.validateToken(testToken);
        JSONObject dataObject = f.getUserInfo(testToken);
        if(dataObject != null){
            System.out.println(dataObject.getString("email"));
            System.out.println(dataObject.getString("name"));
        }
    }
    */

}
