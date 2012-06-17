package uk.co.makroos.android.transport.busalarm.dao.kml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.co.makroos.android.transport.busalarm.domain.Stop;
import android.util.Log;

public abstract class StopKMLSAXHandler<T extends Stop> extends DefaultHandler {

	private final static String TAG = StopKMLSAXHandler.class.getSimpleName();

	enum XmlTag {
		KML, PLACEMARK, NAME, DESCRIPTION, POINT, COORDINATES
	}

	List<XmlTag> context = new ArrayList<XmlTag>();

	private List<T> stops;

	private StringBuilder buffer;
	private String name, description, coordinates;

	public List<T> getStops() {
		return stops;
	}

	@Override
	public void startDocument() throws SAXException {
		Log.d(TAG, "Beginning of KML Document");
		stops = new ArrayList<T>();
	}

	@Override
	public void endDocument() throws SAXException {
		Log.d(TAG, "End of KML Document");
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {

		// TODO: As of Java 1.7 this can be switch
		if (localName.equals("kml")) {
			context.add(XmlTag.KML);
		} else if (localName.equals("Placemark")) {
			context.add(XmlTag.PLACEMARK);
		} else if (localName.equals("name")) {
			context.add(XmlTag.NAME);
			buffer = new StringBuilder();
		} else if (localName.equals("description")) {
			context.add(XmlTag.DESCRIPTION);
			buffer = new StringBuilder();
		} else if (localName.equals("point")) {
			context.add(XmlTag.POINT);
		} else if (localName.equals("coordinates")) {
			context.add(XmlTag.COORDINATES);
			buffer = new StringBuilder();
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

		// TODO: As of Java 1.7 this can be switch
		if (localName.equals("kml")) {
			context.add(XmlTag.KML);
		} else if (localName.equals("Placemark")) {
			context.remove(XmlTag.PLACEMARK);
			stops.add(buildStop(name, description, coordinates));
		} else if (localName.equals("name")) {
			if (context.contains(XmlTag.PLACEMARK)) {
				name = buffer.toString().trim();
			}
			context.remove(XmlTag.NAME);
		} else if (localName.equals("description")) {
			if (context.contains(XmlTag.PLACEMARK)) {
				description = buffer.toString().trim();
			}
			context.remove(XmlTag.DESCRIPTION);
		} else if (localName.equals("point")) {
			context.remove(XmlTag.DESCRIPTION);
		} else if (localName.equals("coordinates")) {
			if (context.contains(XmlTag.PLACEMARK)) {
				coordinates = buffer.toString().trim();
			}
			context.remove(XmlTag.COORDINATES);
		}
	}

	abstract protected T buildStop(String name, String description, String coordinates);

	@Override
	public void characters(char ch[], int start, int length) {

		if (context.contains(XmlTag.PLACEMARK)) {
			switch (context.get(context.size() - 1)) {
			case NAME:
			case DESCRIPTION:
			case COORDINATES:
				buffer.append(new String(ch, start, length));
			}
		}
	}
}
