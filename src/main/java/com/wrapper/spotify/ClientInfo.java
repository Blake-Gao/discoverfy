package com.wrapper.spotify;

import com.microsoft.alm.oauth2.useragent.AuthorizationException;
import com.microsoft.alm.oauth2.useragent.AuthorizationResponse;
import com.microsoft.alm.oauth2.useragent.UserAgent;
import com.microsoft.alm.oauth2.useragent.UserAgentImpl;
import com.wrapper.spotify.Api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MyComputer on 7/22/2017.
 */
public class ClientInfo {

    final String clientID;
    final String clientSecret;
    final String redirectURI;
    List<String> scopes;
    String state;
    String code;
    String accessToken;
    final Api api;

    public ClientInfo(){
        this.clientID = "f6338911bb964c2597c5d49bc7f43b59";
        this.clientSecret = "0e81a234a6c6428ba352398147c724c8";
        this.redirectURI = "http://localhost:8888/callback";
        this.scopes = Arrays.asList("user-read-private", "user-read-email");
        this.state = "defaultstate";
        this.code = "";
        this.accessToken = "";
        api = Api.builder()
                .clientId(this.getClientID())
                .clientSecret(this.getClientSecret())
                .redirectURI(this.getRedirectURI())
                .build();
    }

    /**
     * retrieves the authorization code by authenticating the user and extracting the code
     * from the redirect URI
     */
    public void setCode(String authorizeURL) {
        try {
            final URI authorizationEndpoint = new URI(authorizeURL);
            final URI redirectUri = new URI(this.getRedirectURI());
            final UserAgent userAgent = new UserAgentImpl();
            final AuthorizationResponse authorizationResponse = userAgent.requestAuthorizationCode(authorizationEndpoint, redirectUri);
            this.code = authorizationResponse.getCode();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Api getApi(){ return this.api; }

    public void setAccessToken(String accessToken){ this.accessToken = accessToken; }

    public String getAccessToken(){ return accessToken; }

    public String getCode(){ return this.code; }

    public void setScopes(String ... scopes){
        for (String scope : scopes){
            this.scopes.add(scope);
        }
    }

    public List<String> getScopes(){
        return this.scopes;
    }

    public void setState (String state){
        this.state = state;
    }

    public String getState(){
        return this.state;
    }

    public String getClientID(){
        return this.clientID;
    }

    public String getClientSecret(){
        return this.clientSecret;
    }

    public String getRedirectURI(){
        return this.redirectURI;
    }



}
