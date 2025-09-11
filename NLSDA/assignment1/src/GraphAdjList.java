package assignment;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphAdjList extends AbstractGraph {
    private record Edge(int destination, double weight) {}
    private final ArrayList<LinkedList<Edge>> neighbours;

    public GraphAdjList(int noOfVertices, boolean directed) {
        //Constructor for Graph Adj List
        super(noOfVertices, directed);
        neighbours = new ArrayList<>(noOfVertices);
        for (int i = 0; i < noOfVertices; i++) {
            neighbours.add(new LinkedList<>());
        }

    }

    public void addEdge(int source, int destination, double weight) {
        // If source or destination is below 0 or above Number of vertices it is out of bounds
        if (source < 0 || source >= noOfVertices || destination < 0 || destination >= noOfVertices) {
            System.out.println("Source or destination is out of bounds.");
        }

        // Add edge from source to destination for directed, then only add from detination to source if not directed
        neighbours.get(source).add(new Edge(destination, weight));
        if(!directed) {
            neighbours.get(destination).add(new Edge(source, weight));
        }

    }

    public void removeEdge(int source, int destination) {
        // If source or destination is below 0 or above Number of vertices it is out of bounds
        if (source < 0 || source >= noOfVertices || destination < 0 || destination >= noOfVertices) {
            System.out.println("Source or destination is out of bounds.");
        }

        // Iterate through the list and find the edge to remove
        LinkedList<Edge> sourceNeighbours = neighbours.get(source);
        for (int i = 0; i < sourceNeighbours.size(); i++) {
            if (sourceNeighbours.get(i).destination == destination) {
                // Remove edge if source has the destination same as arguement
                sourceNeighbours.remove(i);
                break;  // Exit the loop once the edge is found and removed
            }
        }

        // If the graph is undirected, remove the reverse edge
        if (!directed) {
            LinkedList<Edge> destinationNeighbours = neighbours.get(destination);
            for (int i = 0; i < destinationNeighbours.size(); i++) {
                if (destinationNeighbours.get(i).destination == source) {
                    destinationNeighbours.remove(i);
                    break;  // Exit the loop once the reverse edge is found and removed
                }
            }
        }
    }




    public double getWeight(int source, int destination) {
        // If source or destination is below 0 or above Number of vertices it is out of bounds
        if (source < 0 || source >= noOfVertices || destination < 0 || destination >= noOfVertices) {
            throw new IndexOutOfBoundsException("Source or destination is out of bounds.");
        }

        // Search all edges for matching destination and return weight if they match
        for (Edge edge : neighbours.get(source)) {
            if (edge.destination == destination) {
                return edge.weight;
            }
        }
        throw new IndexOutOfBoundsException("No edge exists between source and destination.");
    }

    public int[] getNeighbours(int vertex) {
        // If vertex given is out of bound 0 - Number of vertices
        if (vertex < 0 || vertex >= noOfVertices) {
            System.out.println("Vertex index is out of bounds.");
        }

        // List of neightbours
        ArrayList<Integer> neighboursList = new ArrayList<>();

        // Add all edges found in vertex list
        for (Edge edge : neighbours.get(vertex)) {
            neighboursList.add(edge.destination);
        }

        // If not directed check other vertex lists for edges
        if (!directed) {
            for (int i = 0; i < noOfVertices; i++) {
                for (Edge edge : neighbours.get(i)) {
                    if (edge.destination == vertex) {
                        neighboursList.add(i);
                        break;
                    }
                }
            }
        }

        // Converts list of vertices from ArrayList<Integer> to an Int array
        return neighboursList.stream().mapToInt(i -> i).toArray();
    }

    public int getDegree(int vertex) {
        // Initalize counter
        int total_degree = 0;

        // If vertex given is out of bound 0 - Number of vertices
        if (vertex < 0 || vertex >= noOfVertices) {
            System.out.println("Vertex index is out of bounds.");
        }

        // Count total edges in vertex list
        for (Edge edge : neighbours.get(vertex)) {
            total_degree++;
        }

        // Count edges to vertex in other vertex lists
        if (!directed) {
            for (int i = 0; i < noOfVertices; i++) {
                for (Edge edge : neighbours.get(i)) {
                    if (edge.destination == vertex) {
                        total_degree++;
                    }
                }
            }
        }


        return total_degree;
    }

    public boolean isPath(int[] nodes) {

        // Check if nodes are null or less than 2 node because a valid path requires at least 2 nodes
        if (nodes == null || nodes.length < 2) {
            return false;
        }

        // Check all nodes are witin the bounds
        for(int i = 0; i< nodes.length;i++) {
            if (nodes[i] < 0 || nodes[i] >= noOfVertices) {
                System.out.println("Node index out of bounds");
                return false;
            }
        }

        // Check if there is a path between each pair of nodes
        for (int i = 0; i < nodes.length - 1; i++ ) {
            boolean path_value = false;

            // Iterate through neighbours of current node to find edge of next node
            for (Edge edge : neighbours.get(nodes[i])) {
                if (edge.destination == nodes[i + 1]) {
                    path_value = true; // Thus a valid edge exists so break
                    break;
                }
            }
            // If no valid edge was found a path doesn't exist thus return false
            if (!path_value) {
                return false;
            }
        }

        return true;
    }

    public int getNoOfEdges() {
        // Initalize counter
        int total_edges = 0;

        // Iterate through all vertices and sum the number of edges in their adj lists
        for (int i = 0; i < noOfVertices; i++) {
            total_edges += neighbours.get(i).size();
        }

        // If the graph is not directed, each edge is counted twice thus divide by 2
        if (!directed) {
            total_edges = total_edges / 2;
        }

        return total_edges;
    }
}
