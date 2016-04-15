package com.mogtechnologies.assettracing

import com.mogtechnologies.assettracing.graph.TitanGraphFactory
import groovy.json.JsonOutput
import org.apache.tinkerpop.gremlin.groovy.loaders.GremlinLoader
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GroovyGraphOps {
    // This enables the Gremlin magic.
    static {
        GremlinLoader.load()
    }

    // Autowired via setter. I leave this as a blueprints.Graph unless I have to do Titan specific stuff.
    private Graph graph
    private GraphTraversalSource g

    @Autowired
    // I like to autowire via setters because it makes it easy to write spring-free unit tests.
    public void setGraph(TitanGraphFactory gf) {
        this.graph = gf.getGraph()
        this.g = this.graph.traversal()
    }

    public String getPlutosBrothers() {
        def plutosBrothers
        // sweet gremlin all up in my java project. aw yeah...
        plutosBrothers = g.V().has("name", "pluto").both("brother").dedup().values("name").toList()
        return plutosBrothers.toString()
    }
}
