package com.mogtechnologies.assettracing.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;

@Entity("Assets")
@Indexes(
        @Index(value = "_id", fields = @Field("_id"))
)
public class Asset {

    @Id
    private ObjectId id;
    private ArrayList<String> name;
    private ArrayList<FullPath> full_path;

    public ArrayList<String> getNames(){
        return this.name;
    }

    public ArrayList<FullPath> getFullPaths(){
        return this.full_path;
    }

    public String getId(){
        return this.id.toString();
    }
}