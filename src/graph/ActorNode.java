package graph;

import java.util.*;

public class ActorNode {
    private String name;
    private HashSet<MovieNode> movies;

    public ActorNode(String name) {
        this.name = name;
        this.movies = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public HashSet<MovieNode> getMovies() {
        return movies;
    }

    public void addMovie(MovieNode movie) {
        movies.add(movie);
    }

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

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
