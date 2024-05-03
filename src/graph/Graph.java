package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Represents a graph of movies and actors.
 * 
 * @author Andrew Lukashchuk
 * @author Hassan Rizwan
 */
public class Graph {
    /**
     * The movies in the graph.
     */
    HashMap<String, MovieNode> movieMap;

    /**
     * The actors in the graph.
     */
    HashMap<String, ActorNode> actorMap;

    /**
     * Constructs a graph with no movies or actors.
     */
    public Graph() {
        movieMap = new HashMap<>();
        actorMap = new HashMap<>();
    }

    /**
     * Gets the movies in the graph.
     * 
     * @return the movies in the graph
     */
    public Set<MovieNode> getMovies() {
        return new HashSet<>(movieMap.values());
    }

    /**
     * Adds an edge between a movie and an actor in the Graph.
     * 
     * @param movieTitle the title of the movie
     * @param actor the actor to add an edge to
     */
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

    /**
     * Reads data from a CSV file and populates the graph with movies and actors.
     * 
     * @param filename the name of the file to read from
     */
    public void readData(String filename) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            line = br.readLine(); // Read twice to skip header
            while (line != null) {
                // Regex source: https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
                String regex = ",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
                String[] data = line.split(regex); // splits by commas unless they're in quotes

                // Read values from line
                String movieTitle = data[1];
                int year = Integer.parseInt(data[2]);
                String director = data[3];
                String actorName = data[4];
                double rating = Double.parseDouble(data[5]);
                int runtime = Integer.parseInt(data[6]);
                String censor = data[7];
                double totalGross = Double.parseDouble(data[8]);
                String mainGenre = data[9];


                MovieNode movieNode;
                ActorNode actorNode = new ActorNode(actorName);

                // If a movie is already there, add the director and actor, else create a new node
                if (movieMap.containsKey(movieTitle)) {
                    movieNode = movieMap.get(movieTitle);
                    movieNode.addDirector(director);
                    movieNode.addActor(actorNode);
                } else {
                    movieNode = new MovieNode(movieTitle, year, totalGross, runtime, mainGenre, director);
                }

                movieMap.putIfAbsent(movieTitle, movieNode);
                actorMap.putIfAbsent(actorName, actorNode);
                addEdge(movieTitle, actorMap.get(actorName));

                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
