package at.fhj.ims.gae.poi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
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
		Entity entity = new Entity("POI", poi.getId());
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

		List<POI> results = new ArrayList<>();
		for (Entity result : pq.asIterable()) {
			String category = (String) result.getProperty("category");
			String creator = (String) result.getProperty("creator");
			String description = (String) result.getProperty("description");
			String lat = (String) result.getProperty("lat");
			String lon = (String) result.getProperty("lon");
			String name = (String) result.getProperty("name");
			String key = result.getKey().getName();

			POI poi = new POI(key, name, lat, lon, creator, description,
					category);
			results.add(poi);
		}

		return results;
	}

}
