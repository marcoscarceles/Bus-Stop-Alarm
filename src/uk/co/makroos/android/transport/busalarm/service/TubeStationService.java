package uk.co.makroos.android.transport.busalarm.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import uk.co.makroos.android.transport.busalarm.dao.kml.StopKMLSAXHandler;
import uk.co.makroos.android.transport.busalarm.domain.TubeStation;
import android.util.Log;

public class TubeStationService extends AbstractStopService<TubeStation> {

	private static final String TAG = TubeStationService.class.getSimpleName();

	protected TubeStationService() {
	}

	@Override
	protected List<TubeStation> getStopsFromKML(InputStream kmlStream) {

		List<TubeStation> stops = null;

		try {
			// /SAXParser from Factory
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			// XML Reader from SAXParser
			XMLReader xr = sp.getXMLReader();
			// Instantiate Handler
			StopKMLSAXHandler<TubeStation> handler = new StopKMLSAXHandler<TubeStation>() {

				@Override
				protected TubeStation buildStop(String name, String description, String coordinates) {
					TubeStation station = new TubeStation();
					station.setName(name);
					station.setDescription(description);
					station.setCoordinates(coordinates);
					return station;
				}

			};
			xr.setContentHandler(handler);

			/* Parse the xml-data from our URL. */
			xr.parse(new InputSource(kmlStream));

			/* Our NavigationSaxHandler now provides the parsed data to us. */
			stops = handler.getStops();
		} catch (SAXException e) {
			Log.e(TAG, "Could not read stops", e);
		} catch (ParserConfigurationException e) {
			Log.e(TAG, "Could not read stops", e);
		} catch (IOException e) {
			Log.e(TAG, "Could not read stops", e);
		}
		/* Set the result to be displayed in our GUI. */
		Log.d(TAG, "Fetched Stops: " + stops.size());

		return stops;
	}

	@Override
	protected List<TubeStation> getStopsFromCSV(InputStream inputStream) {
		throw new UnsupportedOperationException("CSV Read for Tube Stations not supported on this version");
	}
}
