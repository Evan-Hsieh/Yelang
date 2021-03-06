package com.evanxie.yelang.web.commons;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartWeb {
	public static void main(String[] args){
		Server server = new Server();
		server.setStopAtShutdown(true);
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setReuseAddress(false);
		//Configuration config=ApplicationProperties.get();
		//int port=config.getInt("sysimple.webserver.port", DefaultConfiguration.SYSIMPLE_WEBSERVER_PORT.getInt());	
        connector.setPort(3000);
        server.setConnectors(new Connector[]{connector}); 
        WebAppContext webAppContext;
        if(args.length==0){
        	webAppContext = new WebAppContext("src/main/webapp","/");
            webAppContext.setDescriptor("src/main/webapp/WEB-INF/web.xml");
            webAppContext.setResourceBase("src/main/webapp");
        }else{
        	webAppContext = new WebAppContext();
        	webAppContext.setWar(args[0]);
        }
        webAppContext.setDisplayName("jetty");
        webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);
        server.setHandler(webAppContext);
        try{
            server.start();
            System.out.println("********************************************************");
            System.out.println("The Yelang Has Started !!!");
        }catch(Exception e){
        }
	}
}