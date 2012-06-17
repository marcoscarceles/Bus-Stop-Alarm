package uk.co.makroos.android.transport.busalarm.dao.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import uk.co.makroos.android.transport.busalarm.domain.Stop;
import android.util.Log;

public abstract class StopCSVReader<T extends Stop> {

	public static final String TAG = StopCSVReader.class.getSimpleName();

	public List<T> read(InputStream csvStream) {

		List<T> stops = new ArrayList<T>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
		try {
			String line = reader.readLine(); // Header line ignored
			while ((line = reader.readLine()) != null) {
				T stop = processLine(line);
				if (stop != null) {
					stops.add(stop);
				}
			}
		} catch (IOException e) {
			Log.e(TAG, "Could not read from Stream", e);
		}

		return stops;
	}

	abstract protected T processLine(String line);
}
