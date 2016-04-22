package com.mogtechnologies.assettracing.controllers;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

// Singleton Pattern Database Controller
public class DatabaseController {

    //////////////////////////////////////////////
    //      DEFINITION AND INITIALIZATION       //
    //////////////////////////////////////////////

    private static String dbUrl;
    private static Integer dbPort;
    private static String dbName;
    private static String dbUser;
    private static String dbPassword;

    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    private static Morphia morphia;
    private static Datastore datastore;

    /*
        private static ArrayList<MongoCollection> mongoCollections;
        private static final ArrayList<String> mongoCollectionsNames = new ArrayList<String>();
        */
    // Constructor
    private DatabaseController() {
        // Initialize DB connection
        dbUrl = "192.168.1.131";
        dbPort = 27017;
        dbName = "CatalogedData";
        dbUser = "";
        dbPassword = "";

        mongoClient = new MongoClient( dbUrl, dbPort );
        mongoClient.setWriteConcern( WriteConcern.JOURNALED );
        mongoDatabase = mongoClient.getDatabase( dbName );

        // Start Morphia
        // If in the future we need null or empty values we need to use MappingOptions
        morphia = new Morphia();
        morphia.mapPackage("com.mogtechnologies.assettracing.models");
        datastore = morphia.createDatastore(mongoClient, dbName);

        // Create all indexes annotated by morphia
        datastore.ensureIndexes();
    }

    // Access Point | Thread safe
    public static DatabaseController getInstance(){
        return DatabaseControllerHolder.INSTANCE;
    }

    // Instance of the singleton
    private static class DatabaseControllerHolder{
        private static final DatabaseController INSTANCE = new DatabaseController();
    }

    //////////////////////////////
    //      CLASS METHODS       //
    //////////////////////////////

    // GETTERS
    public MongoClient getClient(){ return mongoClient; }
    public MongoDatabase getDatabase(){ return mongoDatabase; }
    public MongoCollection<Document> getCollection( String s ){ return mongoDatabase.getCollection(s); }
    public MongoCollection getCollection(String s, Class classInfo) {
        return mongoDatabase.getCollection(s, classInfo);
    }
    public Morphia getMorphia() { return morphia; }
    public Datastore getDatastore() { return datastore; }

    // ADDERS
    public void addDocument(String collectionName, Document document){
        mongoDatabase.getCollection(collectionName).insertOne(document);
    }

    // FINDERS (KEEPERS)
    // Here we should let the user get the collection they want and query it
    // I'll just leave this here if a future need rises
    public FindIterable find(String collectionName, Document properties){
        return mongoDatabase.getCollection(collectionName).find(properties);
    }

    public Document findOne(String collectionName, Document properties){
        return mongoDatabase.getCollection(collectionName).find(properties).first();
    }
}