package assignment;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphAdjMatrix extends AbstractGraph {
    private final double[][] adjMatrix;

    public GraphAdjMatrix(int noOfVertices, boolean directed) {
        super(noOfVertices, directed);
        //Constructor
        adjMatrix = new double[noOfVertices][noOfVertices];
        for (int i = 0; i<noOfVertices; i++) {
            for (int j = 0; j < noOfVertices; j++) {
                adjMatrix[i][j] = Double.NaN;
            }
        }
    }

    public void addEdge(int source, int destination, double weight) {
        // Check if either destination or source is out of bounds
        if (source < 0 || source >= noOfVertices || destination < 0 || destination >= noOfVertices) {
            System.out.println("Source or destination is out of bounds.");
            return; //Exit early
        }

        // Add the weight to the source row if the graph is directed
        if (this.directed) {
            adjMatrix[source][destination] = weight;
        }
        // Check the if source and destination are the same
        else {
            if (source == destination) {
                System.out.println("You cannot have an vertex with an edge going back to itself in an undirected graph.");
                return;
            }
            // Add weight to both source and destination positions in an undirected graph
            else {
                adjMatrix[source][destination] = weight;
                adjMatrix[destination][source] = weight;
            }
        }

    }

    public void removeEdge(int source, int destination) {
        // Check if either destination or source is out of bounds
        if (source < 0 || source >= noOfVertices || destination < 0 || destination >= noOfVertices) {
            System.out.println("Source or destination is out of bounds.");
            return; // Exit early
        }

        // Set edge to Nan
        adjMatrix[source][destination] = Double.NaN;

        // Sets edge to Nan taking into account the graph is undirected
        if (!directed) {
            adjMatrix[destination][source] = Double.NaN;
        }
    }

    public double getWeight(int source, int destination) {
        // Check if either destination or source is out of bounds
        if (source < 0 || source >= noOfVertices || destination < 0 || destination >= noOfVertices) {
            System.out.println("Source or destination is out of bounds.");
            return Double.NaN;
        }

        // Returns weight od edge
        return adjMatrix[source][destination];
    }

    public int[] getNeighbours(int vertex) {
        // Check if the vertex is out of bounds
        if (vertex < 0 || vertex >= noOfVertices) {
            System.out.println("Vertex index is out of bounds.");
            return new int[0];
        }

        // Initalize neighbour array list
        ArrayList<Integer> neighbours = new ArrayList<>();

        // For a directed graph add all weights in the vertex that aren't Nan
        // For an undirected graph it is symmetric so no need to check for undirected
        for (int i = 0; i<noOfVertices; i++) {
            if (!Double.isNaN(adjMatrix[vertex][i])) {
                neighbours.add(i);
            }
        }
        
        
        return neighbours.stream().mapToInt(i -> i).toArray();
    }

    public int getDegree(int vertex) {
        // Check if the vertex is out of bounds
        if (vertex < 0 || vertex >= noOfVertices) {
            System.out.println("Vertex index is out of bounds.");
            return -1;
        }

        // Initialize counter of total degree
        int total_degree = 0;

        // Add 1 to the counter if the weight of the vertex position is not Nan
        // No need to take into account undirected graph because undirectered graphs are symettric across the diagonal line
        for (int i = 0; i<noOfVertices;i++) {
            if(!Double.isNaN(adjMatrix[vertex][i])) {
                total_degree++;
            }
        }

        return total_degree;
    }

    public boolean isPath(int[] nodes) {


        // Check if nodes are null or less than 2 node because a valid path requires at least 2 nodes
        if (nodes == null || nodes.length < 2) {
            return false;
        }

        // Check if the node is out of bounds and it exists
        for(int i = 0; i< nodes.length - 1;i++) {
            if (nodes[i] < 0 || nodes[i] >= noOfVertices || nodes[i + 1] < 0 || nodes[i + 1] >= noOfVertices) {
                System.out.println("Node index out of bounds");
                return false;// Return early if out of bounds
            }

            // Check if an edge exists between a pair of node[i] and node[i+1]
            if (Double.isNaN(adjMatrix[nodes[i]][nodes[i + 1]])) {
                return false;
            }
        }


        return true;
    }

    public int getNoOfEdges() {
        // Initalize total counter
        int edgeCount = 0;

        // Iterate thorugh vertices and sum the number of edges in their adj matrix
        for (int i = 0; i<noOfVertices; i++) {
            for (int j = 0; j<noOfVertices; j++) {
                if (!Double.isNaN(adjMatrix[i][j])) {
                    edgeCount++;
                }
            }
        }

        // If the graph is undirected, each edge is counted twice thus divide by 2
        if (!directed) {
            edgeCount = edgeCount / 2;
        }

        return edgeCount;
    }
}
