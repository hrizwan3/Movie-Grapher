package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Graph {
    // might be nice to create one Node class and extend it for ActorNode and MovieNode
    // assumes no duplicate movie or actor names
    HashMap<String, MovieNode> movieMap;
    HashMap<String, ActorNode> actorMap;

    public Graph() {
        this.movieMap = new HashMap<>();
        this.actorMap = new HashMap<>();
    }

    public HashSet<MovieNode> getMovies() {
        return (HashSet) movieMap.values();
    }

    public Collection<ActorNode> getActors() {
        return actorMap.values();
    }

    public void addEdge(String movieTitle, ActorNode actor) {
        MovieNode movie = movieMap.get(movieTitle);
        if (movie == null) {
            System.out.println("Movie not found: " + movieTitle);
            return;
        }

        movie.addActor(actor);
        actor.addMovie(movie);

        actorMap.putIfAbsent(actor.getName(), actor);
        movieMap.putIfAbsent(movieTitle, movie);
    }

    public void readData(String filename) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line = br.readLine(); // skip header
            line = br.readLine();
            while (line != null) {
                // regex source: https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
                String regex = ",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
                String[] data = line.split(regex); // splits by commas unless they're in quotes
                String movieTitle = data[1];
                int year = Integer.parseInt(data[2]);
                String director = data[3];
                String actor = data[4];
                double rating = Double.parseDouble(data[5]); // unused
                int runtime = Integer.parseInt(data[6]);
                String censor = data[7]; // unused
                double totalGross = Double.parseDouble(data[8]);
                String mainGenre = data[9];


                MovieNode movie;
                ActorNode actorNode = new ActorNode(actor);

                // might be a little buggy rn:
                // if a movie is already in the map, grow its directors and actors accordingly
                // else create a new movie
                if (movieMap.containsKey(movieTitle)) {
                    movie = movieMap.get(movieTitle);
                    movie.addDirector(director);
                    movie.addActor(actorNode);
                } else {
                     movie = new MovieNode(movieTitle, year, totalGross, runtime, mainGenre, director);
                }

                movieMap.put(movieTitle, movie);
                actorMap.putIfAbsent(actor, actorNode); // add actor to map if not already there
                addEdge(movieTitle, actorMap.get(actor));

                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
