package main;

import servlets.contact.ManageContactPageServlet;
import servlets.contact.ContactServlet;
import servlets.about.ManageAboutPageServlet;
import servlets.about.AboutServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;
import servlets.home.HomeServlet;
import servlets.home.ManageHomeServlet;
import servlets.post.AddPostServlet;
import servlets.post.EditPostServlet;
import servlets.post.ManagePostServlet;
import servlets.post.PostServlet;

public class Main {

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new DemoServlet()), "/demo");
        context.addServlet(new ServletHolder(new PartialServlet()), "/partial/*");
        context.addServlet(new ServletHolder(new AboutServlet()), "/about");
        context.addServlet(new ServletHolder(new ContactServlet()), "/contact");
        context.addServlet(new ServletHolder(new HomeServlet()), "/home");
        context.addServlet(new ServletHolder(new PostServlet()), "/post");
        context.addServlet(new ServletHolder(new AddPostServlet()), "/addpost");
        context.addServlet(new ServletHolder(new EditPostServlet()), "/editpost");
        context.addServlet(new ServletHolder(new ManageAboutPageServlet()), "/api/about");
        context.addServlet(new ServletHolder(new ManageContactPageServlet()), "/api/contact");
        context.addServlet(new ServletHolder(new ManageHomeServlet()), "/api/home");
        context.addServlet(new ServletHolder(new ManagePostServlet()), "/api/post");

        ContextHandler resourceHandler = new ContextHandler("/static");
        String resource = "./public";
        if (!resource.isEmpty()) {
            resourceHandler.setResourceBase(resource);
            resourceHandler.setHandler(new ResourceHandler());
        }

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);

        server.setHandler(handlers);

        server.start();

        System.out.println("Server started");

        server.join();
    }
}
