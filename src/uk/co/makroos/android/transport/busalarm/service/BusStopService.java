package uk.co.makroos.android.transport.busalarm.service;

import java.io.InputStream;
import java.util.List;

import uk.co.makroos.android.transport.busalarm.dao.csv.StopCSVReader;
import uk.co.makroos.android.transport.busalarm.domain.BusStop;

public class BusStopService extends AbstractStopService<BusStop> {

	private final static String TAG = BusStopService.class.getSimpleName();

	private static BusStopService singleton;

	private BusStopService() {
	};

	public static synchronized BusStopService getInstance() {
		if (singleton == null) {
			singleton = new BusStopService();
		}
		return singleton;
	}

	@Override
	protected List<BusStop> getStopsFromCSV(InputStream csvStream) {
		StopCSVReader<BusStop> reader = new StopCSVReader<BusStop>() {

			private final int LBSL_CODE = 0, STOP_CODE = 1, NAPTAN_CODE = 2, STOP_NAME = 3, EASTING = 4, NORTHING = 5, HEADING = 6, AREA = 7, VIRTUAL = 8;

			@Override
			protected BusStop processLine(String line) {
				BusStop stop = null;
				String[] props = line.split(",");
				if ("0".equals(props[VIRTUAL])) {
					stop = new BusStop();
					stop.setName(props[STOP_NAME]);
					stop.setEasting(Integer.parseInt(props[EASTING]));
					stop.setNorthing(Integer.parseInt(props[NORTHING]));
					stop.recalculateWGS84();	// Calculate Latitude and longitude
												// based on Northing and Easting
				}
				return stop;
			}

		};

		return reader.read(csvStream);
	}

	@Override
	protected List<BusStop> getStopsFromKML(InputStream inputStream) {
		throw new UnsupportedOperationException("CSV Read for Tube Stations not supported on this version");
	}
}
