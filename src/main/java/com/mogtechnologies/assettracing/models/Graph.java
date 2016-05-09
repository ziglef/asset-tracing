package com.mogtechnologies.assettracing.models;

import java.util.ArrayList;

/**
 * Class representing a simple Graph which can contain a list of Nodes and a list of Edges.
 */
public class Graph {

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    /**
     * Creates a new empty Graph.
     */
    public Graph(){
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * Gets the list of Nodes of the Graph.
      * @return The list of Nodes of the Graph.
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Gets an Node of the Graph given its ID.
     * @param id The ID of the Node to get.
     * @return The Node that contains a corresponding ID.
     */
    public Node getNodeById(String id) {
        for(Node n : this.nodes)
            if( n.getId().equals(id) )
                return n;

        return null;
    }

    /**
     * Adds a Node to the Graph.
     * @param n The Node to add.
     */
    public void addNode(Node n){
        this.nodes.add(n);
    }

    /**
     * Sets the Nodes of the Graph to the provided list.
     * @param nodes The list containing the new Nodes.
     */
    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Gets the list of Edges of the Graph.
     * @return The list of Edges of the Graph.
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Gets an Edge of the Graph given its ID.
     * @param id The ID of the Edge to get.
     * @return The Edge that contains a corresponding ID.
     */
    public Edge getEdgeById(String id) {
        for(Edge e : this.edges)
            if( e.getId().equals(id) )
                return e;

        return null;
    }

    /**
     * Adds an Edge to the Graph.
     * @param e The Edge to add.
     */
    public void addEdge(Edge e){
        this.edges.add(e);
    }

    /**
     * Sets the Edges of the Graph to the provided list.
     * @param edges The list containing the new Edges.
     */
    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
}
