package com.wrapper.spotify;

import com.wrapper.spotify.methods.ArtistSearchRequest;
import com.wrapper.spotify.models.Artist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by MyComputer on 7/16/2017.
 */
public class UserInput {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private Artist artist;

    /**
     * sets the artist searched for, requires an access token
     * @throws IOException
     */
    public void setArtist(ClientInfo clientInfo) throws IOException{
        System.out.print("Search artist: ");
        String artistResult = in.readLine();
        final ArtistSearchRequest request = clientInfo.getApi()
                .searchArtists(artistResult)
                .accessToken(clientInfo.getAccessToken())
                .limit(1).build();
        try {
            this.artist = request.get().getItems().get(0);
        } catch (Exception e){
            System.out.println("Error! Could not find artist.");
        }
    }

    public Artist getArtist(){
        return this.artist;
    }


}
