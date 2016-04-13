package com.mogtechnologies.assettracing.graph;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class TitanGraphFactory {
    private static final Logger logger = LoggerFactory.getLogger(com.mogtechnologies.assettracing.graph.TitanGraphFactory.class);

    public static final String PROPS_PATH = "titan-berkeleyje-es.properties";

    // One graph to rule them all...
    private TitanGraph g;

    @PostConstruct
    public void init() {
        try {
            logger.info("Titan Properties Path: {}", PROPS_PATH);
            Configuration conf = new PropertiesConfiguration(PROPS_PATH);
            g = TitanFactory.open(conf);
            logger.info("Titan graph loaded successfully.");
        } catch (ConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public TitanGraph getGraph() {
        return g;
    }

}
