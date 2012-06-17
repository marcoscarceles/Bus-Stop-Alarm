package uk.co.makroos.android.transport.busalarm.service;

import java.io.InputStream;
import java.util.List;

import uk.co.makroos.android.transport.busalarm.domain.Stop;

public abstract class AbstractStopService<T extends Stop> {

	protected AbstractStopService() {
	}

	public List<T> getStopsFromLocalAsset(String resource) {

		InputStream inputStream = BusStopService.class.getClassLoader().getResourceAsStream(resource);
		List<T> stops = null;

		if (resource.endsWith(".csv")) {
			stops = getStopsFromCSV(inputStream);
		} else if (resource.endsWith(".kml")) {
			stops = getStopsFromKML(inputStream);
		}
		return stops;
	}

	protected abstract List<T> getStopsFromCSV(InputStream inputStream);

	protected abstract List<T> getStopsFromKML(InputStream inputStream);

}
