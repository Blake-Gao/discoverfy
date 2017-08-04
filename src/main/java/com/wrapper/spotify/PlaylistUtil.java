package com.wrapper.spotify;

import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.methods.AlbumsForArtistRequest;
import com.wrapper.spotify.methods.RelatedArtistsRequest;
import com.wrapper.spotify.methods.TrackRequest;
import com.wrapper.spotify.models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistUtil {

    private static List<Artist> getRelatedArtists(Api api, String accessToken, Artist artist){
        try {
            final RelatedArtistsRequest relatedArtistsRequest = api
                    .getArtistRelatedArtists(artist.getId())
                    .accessToken(accessToken)
                    .build();
            return relatedArtistsRequest.get();
        } catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        throw new IllegalArgumentException("No related artists found");
    }

    private static Page<SimpleAlbum> getAllAlbumsByArtist(Api api, String accessToken, Artist artist){
        try {
            final AlbumsForArtistRequest albumsForArtistRequest = api
                    .getAlbumsForArtist(artist.getId())
                    .accessToken(accessToken)
                    .build();
            return albumsForArtistRequest.get();
        } catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        throw new IllegalArgumentException("No albums found");
    }

    private static Page<SimpleTrack> getTracksForAlbum (Api api, String accesstoken, String albumId){
        try{
            final AlbumRequest albumRequest = api
                    .getAlbum(albumId)
                    .accessToken(accesstoken)
                    .build();
            return albumRequest.get().getTracks();
        } catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        throw new IllegalArgumentException("No album tracks");
    }

    /**
     * Uses the SimpleTrack to retrieve the full Track object of a track
     * @param simpleTrack
     * @return returns the full Track object of the simplified SimpleTrack object
     */
    private static Track simpleTrackToTrack (Api api, String accessToken, SimpleTrack simpleTrack){
        try{
            final TrackRequest trackRequest = api
                    .getTrack(simpleTrack.getId())
                    .accessToken(accessToken)
                    .build();
            return trackRequest.get();

        } catch (Exception e){
            System.out.println("Someting went wrong: " + e.toString());
        }
        throw new IllegalArgumentException("Could not find track");
    }

    //Puts unique songs into the hashmap, no duplicates
    private static HashMap<String, Integer> listOfTracksToMap(List<SimpleTrack> simpleTrackList,
                                                             HashMap<String, Integer> map,
                                                             Api api, String accessToken){
        for (SimpleTrack simpleTrack : simpleTrackList){
            if (!map.containsKey(simpleTrack.getName())){
                Track track = simpleTrackToTrack(api, accessToken, simpleTrack);
                map.put(track.getName(), track.getPopularity());
            }
        }
        return map;
    }

    //wrapper function
    private static void allTracksToMap(Artist artist){
        //given artist:
        //retrieve all albums by artist of form: List<SimpleAlbum>
        //Loop through each album
            //for each album, retrieve songs of form: List<SimpleTrack>
                //put each list into a hashmap
    }

    private static void getTopTracksFromMap(HashMap<String, Integer>, int limit ){

    }


}
