package com.mogtechnologies.assettracing.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a simple graph node which contains an id and a dictionary of properties.
 */
public class Node {

    private final String id;
    private Map<Object, Object> properties;

    /**
     * Creates a Node given a certain id.
     * @param id The id for the node. Must be unique.
     */
    public Node(String id){
        this.id = id;
        this.properties = new HashMap<>();
    }

    /**
     * Gets the Node's id.
     * @return The id for the Node.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets a property of the Node.
     * @param key Key of the property to get.
     * @return Value of the given key or null if it doesn't exist.
     */
    public Object getProperty(String key){
        return this.properties.get(key);
    }

    /**
     * Adds a property to the Node. If the provided Key already exist the value isn't updated.
     * @param key Key of the property to add.
     * @param value Value of the property to add.
     * @return Value added if successful or null if Key is already in the properties of the Node.
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
     * Updates an existing property of the Node. If the provided key doesn't already exist the value isn't updated.
     * @param key Key of the property to update.
     * @param value Value of the property to update.
     * @return Value updated if successful or null if Key was not present in the properties of the Node.
     */
    public Object updateProperty(String key, String value) {
        if (this.properties.get(key) != null){
            this.properties.put(key, value);
            return value;
        } else
            return null;

    }

    /**
     * Removes a given property from the Node.
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
     * Gets the dictionary of properties for the Node.
     * @return The Map representing the Node's properties.
     */
    public Map<Object, Object> getProperties() {
        return properties;
    }

    /**
     * Sets the properties of the Node.
     * @param properties New Map representing the properties for the Node.
     */
    public void setProperties(Map<Object, Object> properties) {
        this.properties = properties;
    }
}
