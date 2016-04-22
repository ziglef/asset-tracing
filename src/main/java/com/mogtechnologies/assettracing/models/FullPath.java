package com.mogtechnologies.assettracing.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

@Entity("FullPaths")
@Indexes(
        @Index(value = "full_path", fields = @Field("full_path"))
)
public class FullPath {
    @Id
    private ObjectId id;

    private String full_path;
    private String code_machine;
    private String exist;

    public String getFullPath() {
        return full_path;
    }

    public String getCodeMachine() {
        return code_machine;
    }

    public String getExist() {
        return exist;
    }
}