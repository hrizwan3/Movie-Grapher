package graph;

import java.util.*;

/**
 * Represents a movie in the graph.
 * 
 * @author Andrew Lukashchuk
 * @author Hassan Rizwan
 */
public class MovieNode implements Node {
    /**
     * The title of the movie.
     */
    private String title;

    /**
     * The year the movie was released.
     */
    private int year;

    /**
     * The total gross of the movie.
     */
    private double totalGross;

    /**
     * The runtime of the movie.
     */
    private int runtime;

    /**
     * The directors of the movie.
     */
    private HashSet<String> directors;

    /**
     * The genre of the movie.
     */
    private String genre;

    /**
     * The actors in the movie.
     */
    private HashSet<ActorNode> actors;

    /**
     * Constructs a movie node with the given title, year, total gross, runtime, genre, and director.
     * 
     * @param title the title of the movie
     * @param year the year the movie was released
     * @param totalGross the total gross of the movie
     * @param runtime the runtime of the movie
     * @param genre the genre of the movie
     * @param director the director of the movie
     */
    public MovieNode(String title, int year, double totalGross, int runtime, String genre, String director) {
        this.title = title;
        this.year = year;
        this.totalGross = totalGross;
        this.runtime = runtime;
        this.genre = genre;
        this.actors = new HashSet<>();

        this.directors = new HashSet<>();
        this.directors.add(director);
    }

    /**
     * Gets the title of the movie.
     * 
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the year the movie was released.
     * 
     * @return the year the movie was released
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the total gross of the movie.
     * 
     * @return the total gross of the movie
     */
    public double getTotalGross() {
        return totalGross;
    }

    /**
     * Gets the runtime of the movie.
     * 
     * @return the runtime of the movie
     */
    public int getRuntime() {
        return runtime;
    }

    /**
     * Gets the directors of the movie.
     * 
     * @return the directors of the movie
     */
    public Set<String> getDirector() {
        return directors;
    }

    /**
     * Gets the genre of the movie.
     * 
     * @return the genre of the movie
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gets the actors in the movie.
     * 
     * @return the actors in the movie
     */
    public HashSet<ActorNode> getNeighbors() {
        return actors;
    }
    
    /**
     * Adds an actor to the movie's list of actors.
     * 
     * @param actor the actor to add
     */
    public void addActor(ActorNode actor) {
        actors.add(actor);
    }

    /**
     * Adds a director to the movie's list of directors.
     * 
     * @param director the director to add
     */
    public void addDirector(String director) {
        directors.add(director);
    }

    /**
     * Checks if this movie node is equal to another object.
     * 
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        MovieNode movie = (MovieNode) obj;
        return title.equals(movie.title);
    }

    /**
     * Gets the hash code of the movie node.
     * 
     * @return the hash code of the movie node
     */
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    /**
     * Gets the name of the movie.
     * 
     * @return the name of the movie
     */
    @Override
    public String getName() {
        return this.title;
    }
}
