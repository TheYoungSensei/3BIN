package remote;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import core.FichierImpl;

public class WebService {
	public WebService(String rep) throws Exception {
		Server server=new Server(8080);
		
		WebAppContext context=new WebAppContext();
		
		context.setResourceBase("www");
		
		context.addServlet(new ServletHolder(new APIServlet(new FichierImpl(rep))), "/");
		
		server.setHandler(context);
		
		server.start();
	}
	
	public static void main(String[] args) throws Exception {
		new WebService("rep");
	}
}
