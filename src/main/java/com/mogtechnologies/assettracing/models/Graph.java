package com.mogtechnologies.assettracing.models;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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
     * Adds a list of Nodes to the Graph.
     * @param n The Nodes to add.
     */
    public void addNodes(List<Node> n) {
        this.nodes.addAll(n);
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
     * Adds a list of Nodes to the Graph.
     * @param e The Nodes to add.
     */
    public void addEdges(List<Edge> e) {
        this.edges.addAll(e);
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

    /**
     * Returns the Graph as a string with a given type. Current supported types are: JSON
     * @param type Type of representation for the graph.
     * @return A string in the given type representing the Graph.
     */
    public String toString(String type) throws Exception {
        if( !type.equals("JSON" ) )
            throw new Exception("No such output type!");

        return this.toJsonString();
    }

    /**
     * Represents the Graph in JSON. It follows the same format as Sigma.JS
     * @return Returns the Graph represented in JSON.
     */
    private String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
