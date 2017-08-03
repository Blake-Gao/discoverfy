package com.wrapper.spotify;

import com.wrapper.spotify.methods.AlbumsForArtistRequest;
import com.wrapper.spotify.methods.RelatedArtistsRequest;
import com.wrapper.spotify.models.*;

import java.util.List;

public class ArtistMethods {

    /**
     * returns a list of artists
     * @param clientInfo ClientInfo object that stores the client info
     * @param userInput UserInput object, extracts artist chosen by user
     * @return list of related artists
     */
    public static List<Artist> getRelatedArtists(ClientInfo clientInfo, UserInput userInput){
        try {
            final RelatedArtistsRequest relatedArtistsRequest = clientInfo.getApi()
                    .getArtistRelatedArtists(userInput.getArtist().getId())
                    .accessToken(clientInfo.getAccessToken())
                    .build();
            return relatedArtistsRequest.get();
        } catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        throw new IllegalArgumentException("No related artists found");
    }

    /**
     * prints artist related to the artist entered by user
     */
    public static void printRelatedArtists(List<Artist> artists){
            if (artists.isEmpty()) {
                System.out.println("Could not find any related artists!");
            } else {
                System.out.println("The related artists are:");
                for (Artist relArtist : artists) {
                    System.out.println(relArtist.getName());
                }
            }
    }

    public static Page<SimpleAlbum> getAlbumsByArtist(ClientInfo clientInfo, UserInput userInput){
        try {
            final AlbumsForArtistRequest albumsForArtistRequest = clientInfo.getApi()
                    .getAlbumsForArtist(userInput.getArtist().getId())
                    .accessToken(clientInfo.getAccessToken())
                    .build();
            return albumsForArtistRequest.get();
        } catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        throw new IllegalArgumentException("No albums found");
    }


}
