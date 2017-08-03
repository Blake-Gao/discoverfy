package com.wrapper.spotify;

import com.wrapper.spotify.models.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


/**
 * Created by MyComputer on 7/4/2017.
 */
public class Main {

    public static void main (String [] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        /** clientInfo stores the info required to create an access token */
        final ClientInfo clientInfo = new ClientInfo();

        /** create URL for user to authenticate themselves */
        String authorizeURL = clientInfo.getApi().createAuthorizeURL(clientInfo.getScopes(), clientInfo.getState());

        /** creates pop-up window to allow user to authenticate, stores authorization code in clientInfo object */
        clientInfo.setCode(authorizeURL);

        /** create an access token, allows use of spotify methods */
        AuthorizationCodeCredentials authorizationCodeCredentials = null;

        try {
            authorizationCodeCredentials = clientInfo.getApi().authorizationCodeGrant(clientInfo.getCode()).build().get();
            clientInfo.setAccessToken(authorizationCodeCredentials.getAccessToken());
        } catch (Exception e){
            e.printStackTrace();
        }

        /** UserInput object to store UserInput and other methods */
        UserInput userInput = new UserInput();

        /** prompts user to enter their desired artist */
        userInput.setArtist(clientInfo);

        Page<SimpleAlbum> simpleAlbums = ArtistMethods.getAlbumsByArtist(clientInfo, userInput);
        List<SimpleAlbum> simpleAlbumsList = simpleAlbums.getItems();

        /** album tracks test */
        String albumId = simpleAlbumsList.get(0).getId();
        String albumName = simpleAlbumsList.get(0).getName();
        System.out.println("Token: " + clientInfo.getAccessToken());
        System.out.println("Album Id: " + albumId);
        System.out.println("Album name: " + albumName + "\n");
        Page<SimpleTrack> simpleTracks = ArtistMethods.getTracksForAlbum(clientInfo, albumId);
        List<SimpleTrack> simpleTracksList = simpleTracks.getItems();

        for(SimpleTrack track : simpleTracksList){
            System.out.println("Track: " + track.getName());
        }
    }




}
