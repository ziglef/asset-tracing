package com.mogtechnologies.assettracing.graph.utils;

import com.mogtechnologies.assettracing.controllers.DataReader;
import com.mogtechnologies.assettracing.models.Graph;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanTransaction;
import com.thinkaurelius.titan.core.util.TitanCleanup;
import com.mogtechnologies.assettracing.graph.TitanGraphFactory;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import java.io.IOException;

public class CreateAndFillGraph {

    public static void createAndFill(){
        try {
            Configuration conf = new PropertiesConfiguration(TitanGraphFactory.PROPS_PATH);
            TitanGraph g = TitanFactory.open(conf);

            // Uncomment the following if your graph is already populated and you want to clear it out first.
            g.close();
            TitanCleanup.clear(g);
            g = TitanFactory.open(conf);

            TitanTransaction tx = g.newTransaction();

            Graph graphToInsert = DataReader.getGraphFromFile("miserables.json");
            if( graphToInsert == null )
                throw new Exception("Error while parsing JSON file!");

            // INITIAL EXAMPLE //
            /*
            TitanVertex asset1 = tx.addVertex(T.label, "asset", "name", "asset1", "id", 1, "date", "2016-04-03");
            TitanVertex asset12 = tx.addVertex(T.label, "asset", "name", "asset12", "id", 2, "date", "2016-04-10");
            TitanVertex asset13 = tx.addVertex(T.label, "asset", "name", "asset13", "id", 3, "date", "2016-04-15");

            TitanVertex location1 = tx.addVertex(T.label, "location", "name", "location1", "id", 4);
            TitanVertex location2 = tx.addVertex(T.label, "location", "name", "location2", "id", 5);
            TitanVertex location3 = tx.addVertex(T.label, "location", "name", "location3", "id", 6);

            asset1.addEdge("was in", location1, "date", "2016-04-03");
            asset1.addEdge("transformed to", asset12, "date", "2016-04-03");
            asset12.addEdge("was in", location2, "date", "2016-04-10");
            asset12.addEdge("transformed to", asset13, "date", "2016-04-15");
            asset13.addEdge("was in", location3, "date", "2016-04-15");
            */
            tx.commit();

            // GraphOfTheGodsFactory.load(g);
            g.close();
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
