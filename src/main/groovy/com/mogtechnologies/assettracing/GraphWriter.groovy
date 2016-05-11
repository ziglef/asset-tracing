package com.mogtechnologies.assettracing

import com.mogtechnologies.assettracing.models.Edge
import com.mogtechnologies.assettracing.models.Node
import org.apache.tinkerpop.gremlin.groovy.loaders.GremlinLoader
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Direction
import org.apache.tinkerpop.gremlin.structure.Graph

class GraphWriter {
    // This enables the Gremlin magic.
    static {
        GremlinLoader.load()
    }

    private Graph graph
    private GraphTraversalSource g

    public GraphWriter(Graph graph, GraphTraversalSource g){
        this.graph = graph
        this.g = g
    }

    public void writeGraph(OutputStream out, String type) throws Exception {
        if( !type.equals("JSON" ) )
            throw new Exception("No such output type!");

        // Parse titan graph and output results
        com.mogtechnologies.assettracing.models.Graph _graph = new com.mogtechnologies.assettracing.models.Graph();
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();

        g.V().each { node ->
            Node n = new Node(node.property("id").value().toString())

            node.properties().each { property ->
                // println "Adding <${property.key()}, ${property.value()}> to a node!"
                if( !property.key().equals("id") )
                    n.addProperty(property.key().toString(), property.value().toString())
            }
            nodes.add(n)

            node.edges(Direction.OUT).each { edge ->
                Edge e = new Edge(
                        edge.property("id").value().toString(),
                        edge.outVertex().property("id").value().toString(),
                        edge.inVertex().property("id").value().toString()
                )
                edge.properties().each { property ->
                    // println "Adding <${property.key()}, ${property.value()}> to an edge!"
                    if( !property.key().equals("id") )
                        e.addProperty(property.key().toString(), property.value().toString())
                }
                edges.add(e)
            }
        }

        _graph.addNodes(nodes)
        _graph.addEdges(edges)

        out.write(_graph.toString("JSON").getBytes("UTF-8"))
    }
}
