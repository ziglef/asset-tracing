package com.mogtechnologies.assettracing

import com.mogtechnologies.assettracing.controllers.DataReader
import com.mogtechnologies.assettracing.models.Edge
import com.mogtechnologies.assettracing.models.Graph
import com.mogtechnologies.assettracing.models.Node
import com.thinkaurelius.titan.core.PropertyKey
import com.thinkaurelius.titan.core.TitanFactory
import com.thinkaurelius.titan.core.TitanGraph
import com.thinkaurelius.titan.core.TitanVertex
import com.thinkaurelius.titan.core.schema.TitanManagement
import com.thinkaurelius.titan.core.util.TitanCleanup
import org.apache.commons.configuration.Configuration
import org.apache.commons.configuration.ConfigurationException
import org.apache.commons.configuration.PropertiesConfiguration
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.T
import org.apache.tinkerpop.gremlin.structure.Vertex

class CreateAndFillGraph {

    public static void createAndFill(){
        try {
            Configuration conf = new PropertiesConfiguration(TitanGraphFactory.PROPS_PATH);
            TitanGraph graph = TitanFactory.open(conf);

            // Clean the graph before inserting
            graph.close();
            TitanCleanup.clear(graph as TitanGraph);
            graph = TitanFactory.open(conf);

            GraphTraversalSource g = graph.traversal();

            graph.tx().rollback()
            TitanManagement mgmt = graph.openManagement()
            PropertyKey id = mgmt.getOrCreatePropertyKey("id")
            mgmt.buildIndex("byId", Vertex.class).addKey(id).buildCompositeIndex()
            mgmt.commit()

            Graph graphToInsert = DataReader.getGraphFromFile(
                    CreateAndFillGraph
                            .class
                            .getClassLoader()
                            .getResource("miserables.json")
                            .getPath()
            );

            if( graphToInsert == null )
                throw new Exception("Error while parsing JSON file!");

            for(Node n : graphToInsert.getNodes()){
                TitanVertex v = graph.addVertex(T.label, "node", "id", n.getId());
                for( Object o : n.getProperties().keySet() ) {
                    // println "Adding <${o}, ${n.getProperty(o)}> to a vertex!"
                    v.property(o.toString(), n.getProperty(o))
                }
                graph.tx().commit()
            }

            for(Edge e : graphToInsert.getEdges()){
                org.apache.tinkerpop.gremlin.structure.Edge edge =
                    g.V()
                        .has("id", e.getSource()).next()
                        .addEdge("edge_", g.V().has("id", e.getTarget()).next(), "id", e.getId())
                for( Object o : e.getProperties().keySet() ) {
                    // println "Adding <${o}, ${n.getProperty(o)}> to an edge!"
                    edge.property(o.toString(), e.getProperty(o))
                }
                g.tx().commit()
            }
/*
            FileOutputStream out = new FileOutputStream("titan-graph.graphml")
            graph.io(IoCore.graphml()).writer().normalize(true).create().writeGraph(out, graph)
*/
            graph.close();
            System.out.println("Success.");
        } catch (ConfigurationException e) {
            e.printStackTrace();
            System.out.println("Failed initializing graph!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is no such file!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
