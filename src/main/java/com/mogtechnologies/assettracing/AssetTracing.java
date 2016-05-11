package com.mogtechnologies.assettracing;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

public class AssetTracing {

    public static void main(String[] args){
        CreateAndFillGraph.createAndFill();

        System.out.println("Working directory: " + new File("./").getAbsolutePath());
        Server server = new Server(9091);

        WebAppContext context = new WebAppContext();
        context.setDescriptor("/WEB-INF/web.xml");
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        context.addServlet(DefaultServlet.class, "/*");
        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (InterruptedException e) {
            System.out.println("Error while joining the server!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error while starting the server!");
            e.printStackTrace();
        }
    }
}
