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

    // NAMES //
    public ArrayList<String> getNames(){
        return this.name;
    }

    public String getName(int name_no) {
        if(( name_no >= 0 ) && (name_no < this.name.size()))
            return this.name.get(name_no);
        else
            return null;
    }

    public String getCurrentName(){ return this.name.get(0); }

    // FULL PATHS //
    public ArrayList<FullPath> getFullPaths(){
        return this.full_path;
    }

    public FullPath getFullPath(int full_path_no) {
        if(( full_path_no >= 0 ) && (full_path_no < this.name.size()))
            return this.full_path.get(full_path_no);
        else
            return null;
    }

    public FullPath getCurrentFullPath(){ return this.full_path.get(0); }

    // ID //
    public String getId(){
        return this.id.toString();
    }
}