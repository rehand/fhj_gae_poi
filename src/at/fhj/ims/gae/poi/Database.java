package at.fhj.ims.gae.poi;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

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

}
