package graph;

import java.util.*;

public interface Node {
    public String getName();

    public HashSet<? extends Node> getNeighbors();
}
