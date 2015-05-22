package at.fhj.ims.gae.poi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class FHJ_GAE_POIServlet extends HttpServlet {
	private static final char SEP = '/';
	private static final String PATH_POI = "/poi";
	private static final Logger LOG = Logger.getLogger(FHJ_GAE_POIServlet.class.getSimpleName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String pathInfo = req.getPathInfo();
		
		if (pathInfo != null && pathInfo.startsWith(PATH_POI)) {
			Database db = new Database();
			
			// FIXME remove the following line
			POI testPOI = new POI("testId", "name", "123.45", "456.78", "Sepp", "Blabla", "nix");
			
			String queryString = req.getQueryString();
			LOG.info("queryString " + queryString);
			LOG.info("pathInfo " + pathInfo);
			
			int indexOf = pathInfo.lastIndexOf(SEP);
			if (indexOf != 0 && indexOf != pathInfo.length() - 1) {
				String idToFind = pathInfo.substring(indexOf);
				
				// TODO get specific POI
				POI foundPoi = testPOI;
				
				if (foundPoi != null) {
					sendJSONResponse(testPOI, resp);
				} else {
					show404(resp);
				}
			} else if (queryString != null && queryString.length() > 0) {
				// TODO create filter(s)
				
				Map<String, String[]> queryStrings = req.getParameterMap();
				LOG.info("query strings: " + queryStrings);
				
				List<POI> pois = db.find(queryStrings);
				if (pois != null && pois.size() > 0) {
					sendJSONResponse(pois, resp);
				} else {
					show404(resp);
				}
			} else {
				// FIXME remove the following lines
				List<POI> pois = new ArrayList<>();
				pois.add(testPOI);
				
				// TODO get all POIs
				
				if (pois != null && pois.size() > 0) {
					sendJSONResponse(pois, resp);
				} else {
					show404(resp);
				}
			}
			
		} else {
			show501(resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		
		if (pathInfo != null && pathInfo.startsWith(PATH_POI)) {
			Database db = new Database();
			
			int indexOf = pathInfo.lastIndexOf(SEP);
			if (indexOf != 0 && indexOf != pathInfo.length() - 1) {
				String idToUpdate = pathInfo.substring(indexOf);
				
				// TODO do update
			} else {
				ServletInputStream inputStream = req.getInputStream();
				try (InputStreamReader in = new InputStreamReader(inputStream)) {
					Gson gson = new Gson();
					POI poi = gson.fromJson(in, POI.class);
					
					Key id = db.insert(poi);
					
					poi.setId(Long.toString(id.getId()));
					
					sendJSONResponse(poi, resp);
				}
			}
			
		} else {
			show501(resp);
		}
	}
	
	private void sendJSONResponse(Object pois, HttpServletResponse resp) throws IOException {
		Gson gson = new Gson();
		String poisJson = gson.toJson(pois);
		
		resp.setContentType("application/json");
		resp.getWriter().write(poisJson);
	}

	private void show404(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.setStatus(404);
		resp.getWriter().println("Resource not found");
	}
	
	private void show501(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.setStatus(501);
		resp.getWriter().println("Not implemented");
	}
}
