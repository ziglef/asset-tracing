package com.mogtechnologies.assettracing.models;

import java.util.HashMap;

/**
 * Class representing a simple graph Edge which can contain a source node, a target node and a dictionary of properties.
 */
public class Edge {

    private final String id;
    private Node source;
    private Node target;
    private HashMap<Object, Object> properties;

    /**
     * Creates a new Edge given a source and a destination.
     * @param source The Node from which the Edge points.
     * @param target The Node to which the Edge points.
     */
    public Edge(String id, Node source, Node target){
        this.id = id;
        this.source = source;
        this.target = target;
        this.properties = new HashMap<>();
    }

    /**
     * Gets the Edge's id.
     * @return The id for the Edge.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the source of the Edge.
     * @return The Node from which the Edge points.
     */
    public Node getSource() {
        return source;
    }

    /**
     * Sets source node to a new node.
     * @param source The Node from which the Edge will now point.
     */
    public void setSource(Node source) {
        this.source = source;
    }

    /**
     * Gets the target of the Edge.
     * @return The Node to which the Edge points.
     */
    public Node getTarget() {
        return target;
    }

    /**
     * Sets target node to a new node.
     * @param target The Node to which the Edge will now point.
     */
    public void setTarget(Node target) {
        this.target = target;
    }

    /**
     * Gets a property of the Edge.
     * @param key Key of the property to get.
     * @return Value of the given key or null if it doesn't exist.
     */
    public Object getProperty(String key){
        return this.properties.get(key);
    }

    /**
     * Adds a property to the Edge. If the provided Key already exist the value isn't updated.
     * @param key Key of the property to add.
     * @param value Value of the property to add.
     * @return Value added if successful or null if Key is already in the properties of the Edge.
     */
    public Object addProperty(String key, String value){
        if( this.properties.get(key) != null )
            return null;
        else {
            this.properties.put(key, value);
            return value;
        }
    }

    /**
     * Updates an existing property of the Edge. If the provided key doesn't already exist the value isn't updated.
     * @param key Key of the property to update.
     * @param value Value of the property to update.
     * @return Value updated if successful or null if Key was not present in the properties of the Edge.
     */
    public Object updateProperty(String key, String value) {
        if (this.properties.get(key) != null){
            this.properties.put(key, value);
            return value;
        } else
                return null;

    }

    /**
     * Removes a given property from the Edge.
     * @param key Key of the property to be removed.
     * @return Returns the value of the property if successful or null if the given key doesn't exist.
     */
    public Object removeProperty(String key){
        if (this.properties.get(key) != null){
            Object value = this.properties.get(key);
            this.properties.remove(key);
            return value;
        } else
            return null;
    }

    /**
     * Gets the dictionary of properties for the Edge.
     * @return The HashMap representing the Edge's properties.
     */
    public HashMap<Object, Object> getProperties() {
        return properties;
    }

    /**
     * Sets the properties of the Edge.
     * @param properties New HashMap representing the properties for the Edge.
     */
    public void setProperties(HashMap<Object, Object> properties) {
        this.properties = properties;
    }
}
