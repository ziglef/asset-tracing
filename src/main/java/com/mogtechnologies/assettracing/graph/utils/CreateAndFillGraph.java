package com.mogtechnologies.assettracing.graph.utils;


import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.util.TitanCleanup;
import com.thinkaurelius.titan.example.GraphOfTheGodsFactory;
import com.mogtechnologies.assettracing.graph.TitanGraphFactory;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class CreateAndFillGraph {

    public static void createAndFill(){/*
        try {
            Configuration conf = new PropertiesConfiguration(TitanGraphFactory.PROPS_PATH);
            TitanGraph g = TitanFactory.open(conf);

            // Uncomment the following if your graph is already populated and you want to clear it out first.
            g.close();
            TitanCleanup.clear(g);
            g = TitanFactory.open(conf);

            GraphOfTheGodsFactory.load(g);
            g.close();
            System.out.println("Success.");
        } catch (ConfigurationException e) {
            e.printStackTrace();
            System.out.println("Failed initializing graph!");
        }*/
    }

}
