package at.fhj.ims.gae.poi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Database {

	private DatastoreService datastore;

	public Database() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}

	public Key insert(POI poi) {
		Entity entity = new Entity("POI");
		entity.setProperty("category", poi.getCategory());
		entity.setProperty("creator", poi.getCreator());
		entity.setProperty("description", poi.getDescription());
		entity.setProperty("lat", poi.getLat());
		entity.setProperty("lon", poi.getLon());
		entity.setProperty("name", poi.getName());

		return datastore.put(entity);
	}

	public List<POI> find(Map<String, String[]> filterProperties) {
		List<Filter> filters = new ArrayList<>();
		for (String filterKey : filterProperties.keySet()) {
			String[] filterItem = filterProperties.get(filterKey);
			filters.add(new FilterPredicate(filterKey,
					FilterOperator.LESS_THAN_OR_EQUAL, filterItem[0]));

		}

		Filter allFilters;
		if (filters.size() > 1) {
			allFilters = CompositeFilterOperator.and(filters);
		} else {
			allFilters = filters.get(0);
		}

		Query q = new Query("POI").setFilter(allFilters);
		PreparedQuery pq = datastore.prepare(q);
		return queryToList(pq);
	}

	public void delete(String id) {
		Key key = KeyFactory.createKey("POI", Long.parseLong(id));
		datastore.delete(key);
	}

	public POI findById(String id) {
		try {
			Entity entity = findEntityById(id);
			String category = (String) entity.getProperty("category");
			String creator = (String) entity.getProperty("creator");
			String description = (String) entity.getProperty("description");
			String lat = (String) entity.getProperty("lat");
			String lon = (String) entity.getProperty("lon");
			String name = (String) entity.getProperty("name");
			String storedId = Long.toString(entity.getKey().getId());

			return new POI(storedId, name, lat, lon, creator, description,
					category);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Entity findEntityById(String id) throws EntityNotFoundException {
		Key key = KeyFactory.createKey("POI", Long.parseLong(id));
		return datastore.get(key);
	}

	public void update(POI poi) {
		try {
			Entity entity = findEntityById(poi.getId());

			entity.setProperty("category", poi.getCategory());
			entity.setProperty("creator", poi.getCreator());
			entity.setProperty("description", poi.getDescription());
			entity.setProperty("lat", poi.getLat());
			entity.setProperty("lon", poi.getLon());
			entity.setProperty("name", poi.getName());

			datastore.put(entity);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private List<POI> queryToList(PreparedQuery pq) {
		List<POI> results = new ArrayList<>();
		for (Entity result : pq.asIterable()) {
			String category = (String) result.getProperty("category");
			String creator = (String) result.getProperty("creator");
			String description = (String) result.getProperty("description");
			String lat = (String) result.getProperty("lat");
			String lon = (String) result.getProperty("lon");
			String name = (String) result.getProperty("name");
			String key = Long.toString(result.getKey().getId());

			POI poi = new POI(key, name, lat, lon, creator, description,
					category);
			results.add(poi);
		}
		return results;
	}
	
	public List<POI> findAll() {
		Query q = new Query("POI");
		PreparedQuery pq = datastore.prepare(q);
		return queryToList(pq);
	}

}
