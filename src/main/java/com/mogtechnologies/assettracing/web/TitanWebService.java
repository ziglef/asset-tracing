package com.mogtechnologies.assettracing.web;


import com.mogtechnologies.assettracing.CreateAndFillGraph;
import com.mogtechnologies.assettracing.GroovyGraphOps;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/")
@Component
public class TitanWebService {
    @Autowired
    GroovyGraphOps groovyOp;

    @PostConstruct
    private void init() {
        System.out.println("Initialized Titan Web Example Service");
    }

    @GET
    @Path("/listVertices")
    @Produces(MediaType.TEXT_PLAIN)
    public String listVertices(@Context UriInfo info) throws JSONException {
        String res = groovyOp.listVertices();
        return "\"" + res + "\"";
    }

    @GET
    @Path("/plutosBrothers")
    @Produces(MediaType.TEXT_PLAIN)
    public String pipeline(@Context UriInfo info) throws JSONException {
        String res = groovyOp.getPlutosBrothers();
        return "\"" + res + "\"";
    }

    @GET
    @Path("/listEdges")
    @Produces(MediaType.TEXT_PLAIN)
    public String getEdges(@Context UriInfo info) throws JSONException {
        String res = groovyOp.listEdges();
        return "\"" + res + "\"";
    }

    @GET
    @Path("/getJsonGraph")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonGraph(@Context UriInfo info) throws JSONException {
        groovyOp.getJsonGraph();
        return "\"" + "ok" + "\"";
    }
}
