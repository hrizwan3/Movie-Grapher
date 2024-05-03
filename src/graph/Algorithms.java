package graph;

import java.util.*;

/**
 * This class contains algorithms that can be performed on the graph.
 * 
 * @author Andrew Lukashchuk
 * @author Hassan Rizwan
 */
public class Algorithms {
    /**
     * Gets the distance of the input movie from the Dark Knight
     * in the provided graph.
     * 
     * @param g the graph to search
     * @param movie the movie to find the distance from the Dark Knight
     * @return the distance of the input movie from the Dark Knight
     */
    public static int getDarkKnightScore(Graph g, String movie) {
        String darkKnight = "The Dark Knight";
        MovieNode darkKnightMovie = g.movieMap.get(darkKnight);
        MovieNode movieNode = g.movieMap.get(movie);

        // fail if movie not found, else perform bfs (could make a function of this)
        if (movieNode == null) {
            return -1;
        }

        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>(); // generic Node type would be useful here
        queue.add(darkKnightMovie);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                assert node != null;
                if (node.equals(movieNode)) {
                    return level;
                }
                visited.add(node);
                for (Node neighbor : node.getNeighbors()) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
            level++;
        }
        return level;
    }

    // get a movie recommendation based on some actor
    /**
     * Gets recommended movies based on an input actor.
     * 
     * @param g the graph to search
     * @param actor the actor to base recommendations on
     * @return the list of recommended movies
     */
    public static ArrayList<MovieNode> getRecommendedMovies(Graph g, String actor) {
        ArrayList<MovieNode> recommendations = new ArrayList<>();
        ActorNode actorNode = g.actorMap.get(actor);
        if (actorNode == null) return recommendations; // early return if no actor found

        HashSet<Node> visitedMovies = new HashSet<>(actorNode.getNeighbors());
        for (MovieNode movie : actorNode.getNeighbors()) {
            for (ActorNode coActor : movie.getNeighbors()) {
                if (!coActor.equals(actorNode)) {
                    for (MovieNode coActorMovie : coActor.getNeighbors()) {
                        if (!visitedMovies.contains(coActorMovie)) {
                            recommendations.add(coActorMovie);
                            visitedMovies.add(coActorMovie);
                        }
                    }
                }
            }
        }
        return recommendations;
    }

    /**
     * Gets recommended movies based on a list of input movies.
     * Uses triadic closure based on the input movies to generate results.
     * 
     * @param g the graph to search
     * @param movies the list of movies to base recommendations on
     * @return the list of recommended movies
     */
    public static ArrayList<MovieNode> getRecommendedMovies(Graph g, ArrayList<String> movies) {
        ArrayList<MovieNode> inputMovies = new ArrayList<>();
        for (String title : movies) {
            MovieNode movie = g.movieMap.get(title);
            if (movie != null) {
                inputMovies.add(movie);
            }
        }

        HashSet<Node> visitedMovies = new HashSet<>(inputMovies);
        ArrayList<MovieNode> recommendations = new ArrayList<>();

        for (MovieNode movie : inputMovies) {
            for (ActorNode actor : movie.getNeighbors()) {
                for (MovieNode neighborMovie : actor.getNeighbors()) {
                    if (!visitedMovies.contains(neighborMovie)) {
                        recommendations.add(neighborMovie);
                        visitedMovies.add(neighborMovie);
                    }
                }
            }
        }

        return recommendations;
    }
}
