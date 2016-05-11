package com.mogtechnologies.assettracing.controllers;


import com.google.gson.stream.JsonReader;
import com.mogtechnologies.assettracing.models.Edge;
import com.mogtechnologies.assettracing.models.Graph;
import com.mogtechnologies.assettracing.models.Node;
import org.apache.commons.codec.binary.Hex;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class that reads a graph in JSON format from a file and transforms it into a Java object.
 */
public class DataReader {

    /**
     * Reads a Graph from a JSON file.
     * @param pathToDataFile Path to the file containing the Graph data.
     * @return The Graph as a Java object.
     * @throws IOException If the file does not exist.
     */
    public static Graph getGraphFromFile(String pathToDataFile) throws IOException {
        if( pathToDataFile == null )
            return null;

        Graph g = new Graph();

        JsonReader jsonReader = new JsonReader(new FileReader(pathToDataFile));
        jsonReader.beginObject();

        while( jsonReader.hasNext() ){
            String arrName = jsonReader.nextName();
            switch (arrName) {
                case "nodes":
                    g.addNodes(readNodes(jsonReader));
                    break;
                case "edges":
                    g.addEdges(readEdges(jsonReader));
                    break;
                default:
                    return null;
            }
        }

        jsonReader.endObject();
        jsonReader.close();

        return g;
    }

    /**
     /**
     * Reads the Nodes present in the file.
     * @param jsonReader The JsonReader being used by getGraphFromFile.
     * @return The list of Nodes read from the file
     * @throws IOException When there is no next element.
     */
    private static List<Node> readNodes(JsonReader jsonReader) throws IOException {
        List<Node> nodes = new ArrayList<>();

        jsonReader.beginArray();
        while( jsonReader.hasNext() ){
            Node n = readNode(jsonReader);
            while( nodes.indexOf(n) != -1 ){
                n.setId( System.currentTimeMillis() + generateSHA256(n.getId()) );
            }
            nodes.add(n);
        }
        jsonReader.endArray();

        return nodes;
    }

    /**
     * Reads the next Node present in the given JsonReader.
     * @param jsonReader The reader where the next element is a Node.
     * @return The Node that was next on the reader.
     * @throws IOException When there is no next element.
     */
    private static Node readNode(JsonReader jsonReader) throws IOException {
        Node n;
        String id = null;
        Map<Object, Object> properties = new HashMap<>();

        jsonReader.beginObject();
        while( jsonReader.hasNext() ){
            String key = jsonReader.nextName();
            if( key.equals("id") )
                id = jsonReader.nextString();
            else
                properties.put( key, jsonReader.nextString() );
        }
        jsonReader.endObject();

        if( id != null )
            n = new Node(id);
        else {
            String nodeSHA256 = generateSHA256(System.currentTimeMillis() + properties.toString());
            n = new Node(nodeSHA256);
        }

        n.setProperties(properties);
        return n;
    }

    /**
     * Reads the Edges present in the file.
     * @param jsonReader The JsonReader being used by getGraphFromFile.
     * @return The list of Edges read from the file
     */
    private static List<Edge> readEdges(JsonReader jsonReader) throws IOException {
        List<Edge> edges = new ArrayList<>();

        jsonReader.beginArray();
        while( jsonReader.hasNext() ){
            Edge e = readEdge(jsonReader);
            if( e == null )
                continue;
            while( edges.indexOf(e) != -1 ){
                e.setId( System.currentTimeMillis() + generateSHA256(e.getId()) );
            }
            edges.add(e);
        }
        jsonReader.endArray();

        return edges;
    }

    /**
     * Reads the next Edge present in the given JsonReader.
     * @param jsonReader The reader where the next element is a Edge.
     * @return The Edge that was next on the reader or Null if the Edge has invalid format.
     * @throws IOException When there is no next element.
     */
    private static Edge readEdge(JsonReader jsonReader) throws IOException {
        Edge e;
        String id = null;
        String source = null;
        String target = null;
        Map<Object, Object> properties = new HashMap<>();

        jsonReader.beginObject();
        while( jsonReader.hasNext() ){
            String key = jsonReader.nextName();
            switch (key) {
                case "id":
                    id = jsonReader.nextString();
                    break;
                case "source":
                    source = jsonReader.nextString();
                    break;
                case "target":
                    target = jsonReader.nextString();
                    break;
                default:
                    properties.put(key, jsonReader.nextString());
                    break;
            }
        }
        jsonReader.endObject();

        if( id != null ) {
            if( source != null && target != null )
                e = new Edge(id, source, target);
            else
                return null;
        } else {
            String edgeSHA256 = generateSHA256(System.currentTimeMillis() + properties.toString());
            e = new Edge(edgeSHA256, source, target);
        }

        e.setProperties(properties);
        return e;
    }

    /**
     * Given an input string it creates the MD5 hash for the string.
     * @param s The string to calculate the hash for.
     * @return The MD5 hash for the given string.
     */
    private static String generateMD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();

            md.update(s.getBytes(Charset.forName("UTF8")));
            final byte[] digest = md.digest();
            return new String(Hex.encodeHex(digest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Given an input string it creates the SHA-256 hash for the string.
     * @param s The string to calculate the hash for.
     * @return The MD5 hash for the given string.
     */
    private static String generateSHA256(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();

            md.update(s.getBytes(Charset.forName("UTF8")));
            final byte[] digest = md.digest();
            return new String(Hex.encodeHex(digest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
