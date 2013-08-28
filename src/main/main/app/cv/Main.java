package app.cv;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import java.nio.channels.SelectableChannel;

/**
 * @author: sk_y
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Server server =new Server();
        server.setThreadPool(createThreadPool());
        server.setConnectors(createConnectors());
        WebAppContext webapp = new WebAppContext();
        String path =  webapp.getClass().getClassLoader().getResource("").getPath();
        webapp.setResourceBase(path+"/../cv_app");
        webapp.setDescriptor("/WEB-INF/web.xml");
        webapp.setContextPath("/");
        server.setHandler(webapp);
        server.start();
    }



    private static Connector[] createConnectors() {
        return new Connector[]{createDefaultConnector()};
    }

    private static Connector createDefaultConnector() {
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8080);
        return connector;

    }

    private static ThreadPool createThreadPool() {
        QueuedThreadPool threadPool = new QueuedThreadPool(100);
        threadPool.setMinThreads(10);
        return threadPool;
    }
}
