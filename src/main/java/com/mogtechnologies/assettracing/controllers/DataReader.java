package com.mogtechnologies.assettracing.controllers;


import com.google.gson.stream.JsonReader;
import com.mogtechnologies.assettracing.models.Graph;

import java.io.FileReader;
import java.io.IOException;

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
        Graph g = new Graph();

        JsonReader jsonReader = new JsonReader(new FileReader(pathToDataFile));
        jsonReader.beginObject();

        while( jsonReader.hasNext() ){
            String arrName = jsonReader.nextName();
            switch (arrName) {
                case "nodes":
                    readNodes(jsonReader);
                    break;
                case "edges":
                    readEdges(jsonReader);
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
     * Reads the Nodes present in the file.
     * @param jsonReader The JsonReader being used by getGraphFromFile.
     */
    private static void readNodes(JsonReader jsonReader) {

    }

    /**
     * Reads the Edges present in the file.
     * @param jsonReader The JsonReader being used by getGraphFromFile.
     */
    private static void readEdges(JsonReader jsonReader) {

    }
}
