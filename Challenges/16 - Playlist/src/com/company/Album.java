package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {

    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<Song>();
    }

    public boolean addSong(String title, double duration) {
        if(findSong(title) == null) {
            this.songs.add(new Song(title, duration));
            return true;
        }
        return false;
    }

    private Song findSong(String title) {
        for(Song indexSong : this.songs) {
            if (indexSong.getTitle().equalsIgnoreCase(title)) {
                return indexSong;
            }
        }
        return null;
    }

    public boolean addToPlayList(int tackNumber, LinkedList<Song> playlist) {
        tackNumber--;
        if(tackNumber >= 0 && tackNumber < this.songs.size()) {
            playlist.add(this.songs.get(tackNumber));
            return true;
        }
        return false;
    }

    public boolean addToPlayList(String titleSong, LinkedList<Song> playlist) {
        Song wasFound = findSong(titleSong);
        if(wasFound != null) {
            playlist.add(wasFound);
            return true;
        }
        return false;
    }

}
