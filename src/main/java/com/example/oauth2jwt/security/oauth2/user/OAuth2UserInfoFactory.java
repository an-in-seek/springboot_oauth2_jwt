package com.example.oauth2jwt.security.oauth2.user;

import com.example.oauth2jwt.security.oauth2.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

import static com.example.oauth2jwt.common.enums.SocialType.GOOGLE;
import static com.example.oauth2jwt.common.enums.SocialType.FACEBOOK;
import static com.example.oauth2jwt.common.enums.SocialType.GITHUB;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(GOOGLE.isEquals(registrationId)) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (FACEBOOK.isEquals(registrationId)) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (GITHUB.isEquals(registrationId)) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
