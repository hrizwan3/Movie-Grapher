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
     * Gets the distances from the input movie by performing BFS.
     * 
     * @param g the graph to search
     * @param startMovieName the movie to start the search from
     * @return a map of movie names to their distances from the input movie
     */
    public static Map<String, Integer> breadthFirstSearch(Graph g, String startName) {
        Node startNode = g.movieMap.get(startName);
        if (startNode == null) {
            startNode = g.actorMap.get(startName);
        }
        Map<String, Integer> distances = new HashMap<>();

        // Check if start movie exists in g
        if (startNode == null) {
            return distances;
        }

        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.add(startNode);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                assert node != null;
                distances.put(node.getName(), level);
                visited.add(node);
                for (Node neighbor : node.getNeighbors()) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
            level++;
        }
        return distances;
    }

    /**
     * Gets the distance of the input movie from the Dark Knight
     * in the provided graph.
     * 
     * @param g the graph to search
     * @param sourceMovie the movie to find the distance from
     * @param targetMovie the movie to find the distance to
     * @return the distance of the input movie from the Dark Knight or -1 if the movie is not found
     */
    public static int getScoreFromMovie(Graph g, String sourceMovie, String targetMovie) {
        Map<String, Integer> distances = breadthFirstSearch(g, sourceMovie);
        if (!distances.containsKey(targetMovie)) {
            return -2;
        }
        return distances.get(targetMovie);
    }

    /**
     * Gets the farthest movies from the input movie in the provided graph.
     * 
     * @param g the graph to search
     * @param sourceMovie the movie to find the farthest movies from
     * @return the set of farthest movies from the input movie
     */
    public static Set<String> farthestMoviesFromMovie(Graph g, String sourceMovie) {
        Map<String, Integer> distances = breadthFirstSearch(g, sourceMovie);
        int max = 0;
        for (int distance : distances.values()) {
            max = Math.max(max, distance);
        }
        if (max % 2 == 1) {
            max -= 1;
        }

        Set<String> farthestMovieSet = new HashSet<>();
        for (HashMap.Entry<String, Integer> entry : distances.entrySet()) {
            if (entry.getValue().equals(max)) {
                farthestMovieSet.add(entry.getKey());
            }
        }

        return farthestMovieSet;
    }

    /**
     * Gets the farthest actors from the input actor in the provided graph.
     * 
     * @param g the graph to search
     * @param sourceActor the actor to find the farthest actors from
     * @return the set of farthest actors from the input actor
     */
    public static Set<String> farthestActorsFromActor(Graph g, String sourceActor) {
        Map<String, Integer> distances = breadthFirstSearch(g, sourceActor);
        int max = 0;
        for (int distance : distances.values()) {
            max = Math.max(max, distance);
        }
        if (max % 2 == 1) {
            max -= 1;
        }

        Set<String> farthestMovieSet = new HashSet<>();
        for (HashMap.Entry<String, Integer> entry : distances.entrySet()) {
            if (entry.getValue().equals(max)) {
                farthestMovieSet.add(entry.getKey());
            }
        }

        return farthestMovieSet;
    }

    /**
     * Gets the farthest movies from the input actor in the provided graph.
     * 
     * @param g the graph to search
     * @param sourceActor the actor to find the farthest movies from
     * @return the set of farthest movies from the input actor
     */
    public static Set<String> farthestMoviesFromActor(Graph g, String sourceActor) {
        Map<String, Integer> distances = breadthFirstSearch(g, sourceActor);
        int max = 0;
        for (int distance : distances.values()) {
            max = Math.max(max, distance);
        }
        if (max % 2 == 0) {
            max -= 1;
        }

        Set<String> farthestMovieSet = new HashSet<>();
        for (HashMap.Entry<String, Integer> entry : distances.entrySet()) {
            if (entry.getValue().equals(max)) {
                farthestMovieSet.add(entry.getKey());
            }
        }

        return farthestMovieSet;
    }

    /**
     * Gets the farthest actors from the input movie in the provided graph.
     * 
     * @param g the graph to search
     * @param sourceMovie the movie to find the farthest actors from
     * @return the set of farthest actors from the input movie
     */
    public static Set<String> farthestActorsFromMovie(Graph g, String sourceMovie) {
        Map<String, Integer> distances = breadthFirstSearch(g, sourceMovie);
        int max = 0;
        for (int distance : distances.values()) {
            max = Math.max(max, distance);
        }
        if (max % 2 == 0) {
            max -= 1;
        }

        Set<String> farthestActorSet = new HashSet<>();
        for (HashMap.Entry<String, Integer> entry : distances.entrySet()) {
            if (entry.getValue().equals(max)) {
                farthestActorSet.add(entry.getKey());
            }
        }

        return farthestActorSet;
    }

    /**
     * Gets top 10 recommended movies based on an input actor using triadic closure.
     * 
     * @param g the graph to search
     * @param actor the actor to base recommendations on
     * @return the list of recommended movies
     */
    public static List<MovieNode> getRecommendedMovies(Graph g, String actor) {
        List<MovieNode> recommendations = new ArrayList<>();
        HashMap<MovieNode, Integer> recommendationRank = new HashMap<>();
        ActorNode actorNode = g.actorMap.get(actor);
        if (actorNode == null) {
            return recommendations;
        }

        for (MovieNode movie : actorNode.getNeighbors()) {
            for (ActorNode coActor : movie.getNeighbors()) {
                if (!coActor.equals(actorNode)) {
                    for (MovieNode coActorMovie : coActor.getNeighbors()) {
                        if (!recommendationRank.containsKey(coActorMovie)) {
                            recommendationRank.put(coActorMovie, 0);
                        } else {
                            recommendationRank.put(coActorMovie, recommendationRank.get(coActorMovie) + 1);
                        }
                    }
                }
            }
        }

        List<Map.Entry<MovieNode, Integer>> entries = new ArrayList<>(recommendationRank.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<MovieNode, Integer>>() {
            public int compare(Map.Entry<MovieNode, Integer> a, Map.Entry<MovieNode, Integer> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });

        int topLimit = Math.min(10, entries.size());  
        for (int i = 0; i < topLimit; i++) {
            recommendations.add(entries.get(i).getKey());
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
    public static List<MovieNode> getRecommendedMovies(Graph g, List<String> movies) {
        List<MovieNode> recommendations = new ArrayList<>();
        HashMap<MovieNode, Integer> recommendationRank = new HashMap<>();
        List<MovieNode> inputMovies = new ArrayList<>();
        for (String title : movies) {
            MovieNode movie = g.movieMap.get(title);
            if (movie != null) {
                inputMovies.add(movie);
            }
        }

        for (MovieNode movie : inputMovies) {
            for (ActorNode actor : movie.getNeighbors()) {
                for (MovieNode neighborMovie : actor.getNeighbors()) {
                    if (!recommendationRank.containsKey(neighborMovie)) {
                        recommendationRank.put(neighborMovie, 0);
                    } else {
                        recommendationRank.put(neighborMovie, recommendationRank.get(neighborMovie) + 1);
                    }
                }
            }
        }

        List<Map.Entry<MovieNode, Integer>> entries = new ArrayList<>(recommendationRank.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<MovieNode, Integer>>() {
            public int compare(Map.Entry<MovieNode, Integer> a, Map.Entry<MovieNode, Integer> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });

        int topLimit = Math.min(10, entries.size());  
        for (int i = 0; i < topLimit; i++) {
            recommendations.add(entries.get(i).getKey());
        }
        
        return recommendations;
    }
}
