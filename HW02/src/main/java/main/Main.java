package main;

import java.util.logging.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import accounts.AccountService;
import accounts.UserProfile;
import servlets.SigUpServlet;
import servlets.SignInServlet;

public class Main {
    private static final int SERVER_PORT = 8080;
    private static final String PUBLIC_DIR = "public_html";

    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getGlobal();
		AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin", "admin"));
        accountService.addNewUser(new UserProfile("test", "test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SigUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_DIR);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{ resourceHandler, context });

        Server server = new Server(SERVER_PORT);
        server.setHandler(handlers);

        server.start();
        logger.info("Server started");
        server.join();
    }
}