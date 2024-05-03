package graph;

import java.util.*;

/**
 * Represents a node in the graph.
 * 
 * @author Andrew Lukashchuk
 * @author Hassan Rizwan
 */
public interface Node {
    /**
     * Gets the name of the node.
     * 
     * @return the name of the node
     */
    public String getName();

    /**
     * Gets the neighbors of the node.
     * 
     * @return the neighbors of the node
     */
    public Set<? extends Node> getNeighbors();
}
