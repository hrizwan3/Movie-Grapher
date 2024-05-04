package graph;

/**
 * Represents an edge in the graph.
 * 
 * @author Andrew Lukashchuk
 * @author Hassan Rizwan
 */
public class Edge {
    /**
     * The source node of the edge.
     */
    private Node source;

    /**
     * The target node of the edge.
     */
    private Node target;

    /**
     * Constructs an edge with the given source and target nodes.
     * 
     * @param source the source node of the edge
     * @param target the target node of the edge
     */
    public Edge (Node source, Node target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Gets the source node of the edge.
     * 
     * @return the source node of the edge
     */
    public Node getSource() {
        return this.source;
    }

    /**
     * Gets the target node of the edge.
     * 
     * @return the target node of the edge
     */
    public Node getTarget() {
        return this.target;
    }   
}
