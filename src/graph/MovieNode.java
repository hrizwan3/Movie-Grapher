package graph;

import java.util.*;

public class MovieNode {
    private String title;
    private int year;
    private double totalGross;
    private int runtime;
    private HashSet<String> directors;
    private String genre;
    private HashSet<ActorNode> actors;

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

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getTotalGross() {
        return totalGross;
    }

    public int getRuntime() {
        return runtime;
    }

    public HashSet<String> getDirector() {
        return directors;
    }

    public String getGenre() {
        return genre;
    }

    public HashSet<ActorNode> getActors() {
        return actors;
    }

    public void addActor(ActorNode actor) {
        actors.add(actor);
    }

    public void addDirector(String director) {
        directors.add(director);
    }

    // may tweak this method to include more fields
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

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}