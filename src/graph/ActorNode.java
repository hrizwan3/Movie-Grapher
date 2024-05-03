package graph;

import java.util.*;

/**
 * Represents an actor in the graph.
 * 
 * @author Andrew Lukashchuk
 * @author Hassan Rizwan
 */
public class ActorNode implements Node {
    /**
     * The name of the actor.
     */
    private String name;

    /**
     * The movies the actor has acted in.
     */
    private HashSet<MovieNode> movies;

    /**
     * Constructs an actor node with the given name.
     * 
     * @param name the name of the actor
     */
    public ActorNode(String name) {
        this.name = name;
        this.movies = new HashSet<>();
    }

    /**
     * Gets the name of the actor.
     * 
     * @return the name of the actor
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the movies the actor has acted in.
     * 
     * @return the movies the actor has acted in
     */
    public HashSet<MovieNode> getNeighbors() {
        return movies;
    }

    /**
     * Adds a movie to the actor's list of movies.
     * 
     * @param movie the movie to add
     */
    public void addMovie(MovieNode movie) {
        movies.add(movie);
    }

    /**
     * Checks if this actor node is equal to another object.
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
        ActorNode actor = (ActorNode) obj;
        return name.equals(actor.name);
    }

    /**
     * Gets the hash code of the actor node.
     * 
     * @return the hash code of the actor node
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
