package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Graph {
    // might be nice to create one Node class and extend it for ActorNode and MovieNode
    private HashMap<MovieNode, HashSet<ActorNode>> movies;
    private HashMap<ActorNode, HashSet<MovieNode>> actors;

    public Graph() {
        this.movies = new HashMap<>();
        this.actors = new HashMap<>();
    }

    public Set<MovieNode> getMovies() {
        return movies.keySet();
    }

    public Set<ActorNode> getActors() {
        return actors.keySet();
    }

    public void addEdge(MovieNode movie, ActorNode actor) {
        movie.addActor(actor);
        actor.addMovie(movie);

        HashSet<MovieNode> actorMovies = actors.get(actor);
        HashSet<ActorNode> movieActors = movies.get(movie);

        if (actorMovies == null) {
            actorMovies = new HashSet<>();
            actors.put(actor, actorMovies);
        } else {
            actorMovies.add(movie);
            actors.put(actor, actorMovies);
        }

        if (movieActors == null) {
            movieActors = new HashSet<>();
            movies.put(movie, movieActors);
        } else {
            actorMovies.add(movie);
            movies.put(movie, movieActors);
        }
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


                MovieNode movie = new MovieNode(movieTitle, year, totalGross, runtime, mainGenre, director);
                ActorNode actorNode = new ActorNode(actor);

                addEdge(movie, actorNode);

                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
