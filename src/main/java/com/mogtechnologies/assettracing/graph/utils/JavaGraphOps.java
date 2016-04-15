package com.mogtechnologies.assettracing.graph.utils;


import com.mogtechnologies.assettracing.graph.TitanGraphFactory;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class JavaGraphOps {
    // Autowired via setter. I leave this as a blueprints.Graph unless I have to do Titan specific stuff.
    private Graph g;

    @Autowired
    // I like to autowire via setters because it makes it easy to write spring-free unit tests.
    public void setGraph(TitanGraphFactory gf) {
        this.g = gf.getGraph();
    }

    public String listVertices() {
        List<String> vs = new ArrayList<String>();
        Iterator<Vertex> itty = g.vertices();
        Vertex v;
        while (itty.hasNext()) {
            v = itty.next();
            vs.add((String) v.property("name").value());
        }

        return vs.toString()
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
    }

    public String listEdges() {
        List<String> es = new ArrayList<String>();
        Iterator<Edge> itty = g.edges();
        Edge e;
        while (itty.hasNext()) {
            e = itty.next();
            es.add(e.outVertex().property("name").value().toString());
            es.add(e.inVertex().property("name").value().toString());
            //es.add((String) e.property("name").value());
        }

        return es.toString()
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
    }
}