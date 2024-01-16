package main;

import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.MirrorServlet;


public class Main {
    private static final int SERVER_PORT = 8080;
    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getGlobal();
        Server server = new Server(SERVER_PORT);
        ServletContextHandler context =
                new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new MirrorServlet()), "/mirror");
        server.setHandler(context);

        server.start();
        logger.info("Server started");
        server.join();
    }
}