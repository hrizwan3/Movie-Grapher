import java.util.*;

import graph.*;

/**
 * The main class for the program.
 * 
 * @author Andrew Lukashchuk
 * @author Hassan Rizwan
 */
public class Main {
    public static void main(String[] args) {
        // Initialize the graph to the imdb dataset
        Graph graph = new Graph();
        graph.readData("data/imdb_cleaned.csv");
        boolean running = true;

        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("Enter a command or type 'help' for a list of commands:");
            System.out.print("$ ");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.println("What are the farthest movies from (input movie)?");
                    System.out.print("Input Movie: $ ");
                    command = scanner.nextLine();
                    parseCommand(1, command, graph);
                    break;
                case "2":
                    System.out.println("What are the farthest actors from (input actor)?");
                    System.out.print("Input Actor: $ ");
                    command = scanner.nextLine();
                    parseCommand(2, command, graph);
                    break;
                case "3":
                    System.out.println("What are the farthest movies from (input actor)?");
                    System.out.print("Input Actor: $ ");
                    command = scanner.nextLine();
                    parseCommand(3, command, graph);
                    break;
                case "4":
                    System.out.println("What are the farthest actors from (input movie)?");
                    System.out.print("Input movie: $ ");
                    command = scanner.nextLine();
                    parseCommand(4, command, graph);
                    break;
                case "5":
                    System.out.println("What is the shortest path between (input movie) and (input movie)?");
                    System.out.print("Input movie 1: $ ");
                    command = scanner.nextLine();
                    parseCommand(5, command, graph);
                    break;
                case "6":
                    System.out.println("What is the shortest path between (input actor) and (input actor)?");
                    System.out.print("Input actor 1: $ ");
                    command = scanner.nextLine();
                    parseCommand(6, command, graph);
                    break;
                case "7":
                    System.out.println("What is the shortest path between (input movie) and (input actor)?");
                    System.out.print("Input movie: $ ");
                    command = scanner.nextLine();
                    parseCommand(7, command, graph);
                    break;
                case "8":
                    System.out.println("What are some recommended movies for (input actor)?");
                    System.out.print("Input actor: $ ");
                    command = scanner.nextLine();
                    parseCommand(8, command, graph);
                    break;
                case "9":
                    System.out.println("What are some recommended movies based on (input movie(s))?");
                    System.out.print("Input movie 1: $ ");
                    command = scanner.nextLine();
                    parseCommand(9, command, graph);
                    break;
                case "help":
                    System.out.println("1. What are the farthest movies from (input movie)?");
                    System.out.println("2. Who is the farthest actor from (input actor)?");
                    System.out.println("3. What is the farthest movie from (input actor)?");
                    System.out.println("4. Who is the farthest actor from (input movie)?");
                    System.out.println("5. What is the shortest path between (input movie) and (input movie)?");
                    System.out.println("6. What is the shortest path between (input actor) and (input actor)?");
                    System.out.println("7. What is the shortest path between (input movie) and (input actor)?");
                    System.out.println("8. What are some recommended movies for (input actor)?");
                    System.out.println("9. What are some recommended movies based on (input movie(s))?");
                    System.out.println("help. Display this list of commands");
                    System.out.println("exit. Exit the program");
                    break;
                case "exit":
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }

    /**
     * Parses the command and calls the appropriate algorithm.
     * 
     * @param command the command to parse
     * @param input the input to the command
     * @param g the graph to search
     */
    public static void parseCommand(int command, String input, Graph g) {
        switch (command) {
            case 1:
                Set<String> farthestMovies = Algorithms.farthestMoviesFromMovie(g, input);
                System.out.println("The farthest movies from " + input + " are:");
                for (String movie : farthestMovies) {
                    System.out.println("    " + movie);
                }
                break;
            case 2:
                Set<String> farthestActors = Algorithms.farthestActorsFromActor(g, input);
                System.out.println("The farthest actors from " + input + " are:");
                for (String actor : farthestActors) {
                    System.out.println("    " + actor);
                }
                break;
            case 3:
                Set<String> farthestMovies3 = Algorithms.farthestMoviesFromActor(g, input);
                System.out.println("The farthest movies from " + input + " are:");
                for (String movie : farthestMovies3) {
                    System.out.println("    " + movie);
                }
                break;
            case 4:
                Set<String> farthestActors4 = Algorithms.farthestActorsFromMovie(g, input);
                System.out.println("The farthest actors from " + input + " are:");
                for (String actor : farthestActors4) {
                    System.out.println("    " + actor);
                }
                break;
            case 5:
                System.out.print("Input movie 2: $ ");
                Scanner scanner = new Scanner(System.in);
                String movie2 = scanner.nextLine();
                int shortestPath = (int) (Algorithms.getScoreFromMovie(g, input, movie2) / 2);
                System.out.println("The shortest path between " + input + " and " + movie2 + " is: " + shortestPath);
                break;
            case 6:
                System.out.print("Input actor 2: $ ");
                Scanner scanner2 = new Scanner(System.in);
                String actor2 = scanner2.nextLine();
                int shortestPath2 = (int) (Algorithms.getScoreFromMovie(g, input, actor2) / 2);
                System.out.println("The shortest path between " + input + " and " + actor2 + " is: " + shortestPath2);
                break;
            case 7:
                System.out.print("Input actor: $ ");
                Scanner scanner3 = new Scanner(System.in);
                String actor3 = scanner3.nextLine();
                double shortestPath3 = (((double) Algorithms.getScoreFromMovie(g, input, actor3)) / 2.0);
                System.out.println("The shortest path between " + input + " and " + actor3 + " is: " + shortestPath3);
                break;
            case 8:
                List<MovieNode> recommendedMovies = Algorithms.getRecommendedMovies(g, input);
                System.out.println("The recommended movies for " + input + " are:");
                for (MovieNode movie : recommendedMovies) {
                    System.out.println("    " + movie.getTitle());
                }
                break;
            case 9:
                System.out.print("Input movie 2: $ ");
                Scanner scanner4 = new Scanner(System.in);
                List<String> movies = new ArrayList<>();
                movies.add(input);
                String movie3 = scanner4.nextLine();
                movies.add(movie3);
                System.out.print("Input movie 3 (Leave Blank if Just 2 Desired): $ ");
                String movie4 = scanner4.nextLine();
                movies.add(movie4);
                List<MovieNode> recommendedMovies2 = Algorithms.getRecommendedMovies(g, movies);
                System.out.println("The recommended movies for " + input + ", " + movie3 + ", and " + movie4 + " are:");
                for (MovieNode movie : recommendedMovies2) {
                    System.out.println("    " + movie.getTitle());
                }
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }
}
