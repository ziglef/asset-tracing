package com.mogtechnologies.assettracing.web;


import com.mogtechnologies.assettracing.GroovyGraphOps;
import com.mogtechnologies.assettracing.graph.utils.JavaGraphOps;
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

    @Autowired
    JavaGraphOps javaOp;

    @PostConstruct
    private void init() {
        System.out.println("Initialized Titan Web Example Service");
    }

    @GET
    @Path("/listVertices")
    @Produces(MediaType.TEXT_PLAIN)
    public String listVertices(@Context UriInfo info) throws JSONException {
        String res = javaOp.listVertices();
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
    public String getGraph(@Context UriInfo info) throws JSONException {
        String res = javaOp.listEdges();
        return "\"" + res + "\"";
    }
}
