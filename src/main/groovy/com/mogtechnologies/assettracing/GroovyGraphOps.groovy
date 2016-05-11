package com.mogtechnologies.assettracing

import org.apache.tinkerpop.gremlin.groovy.loaders.GremlinLoader
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GroovyGraphOps {
    // This enables the Gremlin magic.
    static {
        GremlinLoader.load()
    }

    private Graph graph
    private GraphTraversalSource g

    @Autowired
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

    public String listVertices() {
        return g.V().toList();
    }

    public String listEdges() {
        return g.E().toList();
    }

    public void getJsonGraph(){
        GraphWriter gw = new GraphWriter(graph, g);
        gw.writeGraph(
                new FileOutputStream("C:\\Users\\rgrandao\\dev\\asset-tracing\\src\\main\\webapp\\titan-graph.json"),
                "JSON"
        )
        /*
            OutputStream out = new FileOutputStream("C:\\Users\\rgrandao\\dev\\asset-tracing\\src\\main\\webapp\\titan-graph.json");
            graph.io(IoCore.graphson()).writer().create().writeGraph(out, graph)
        */
    }
}
