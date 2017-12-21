package remote;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.Fichier;

public class APIServlet extends HttpServlet {
	
	private Fichier fichier;

	APIServlet(Fichier fichier) {
		this.fichier=fichier;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getParameter("action");
		resp.setCharacterEncoding("utf-8");
		String chemin;
		try {
			switch(action) {
			case "liste":
				String[] all=fichier.liste(req.getPathInfo()==null?".":req.getPathInfo());
				resp.setContentType("text/plain");
				resp.getOutputStream().write( String.join("\n", all).getBytes(StandardCharsets.UTF_8));
				break;
			case "charge":
				chemin=req.getParameter("chemin");
				resp.setContentType("text/plain");
				resp.getOutputStream().write(Base64.getEncoder().encode(fichier.charge(chemin)));
				break;
			case "sauve":
				chemin=req.getParameter("chemin");
				String donnees=req.getParameter("donnees");
				byte[] bytes=Base64.getDecoder().decode(donnees);
				fichier.sauve(chemin, bytes);
				break;
			case "efface":
				chemin=req.getParameter("chemin");
				fichier.efface(chemin);
				break;
			}
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.setContentType("text/plain");
			StringWriter strOut = new StringWriter();
			e.printStackTrace(new PrintWriter(strOut));
			String msg=strOut.toString();
			byte[] msgBytes=msg.getBytes("UTF-8");
			resp.setContentLength(msgBytes.length);
			resp.getOutputStream().write(msgBytes);
		}
	}
}
