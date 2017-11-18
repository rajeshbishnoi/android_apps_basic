package ysic47.currencyconvertor;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

	Boolean currentElement = false;
	String currentValue = null;
int flag=0;
	public static Gagan GetterSetter = null;

	public static Gagan getGetterSetter() {
		return GetterSetter;
	}

	/**
	 * Called when tag starts ( ex:- <name>AndroidPeople</name> -- <name> )
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		currentElement = true;
flag++;
		if (localName.equalsIgnoreCase("Envelope")) {
			/** Start */
			GetterSetter = new Gagan();

		}

		if (localName.equals("Cube")&&flag>2) {

			GetterSetter.setCurrArr(attributes.getValue("currency"));
			GetterSetter.setRateArr(attributes.getValue("rate"));

		}

	}

	/**
	 * Called when tag closing ( ex:- <name>AndroidPeople</name> -- </name> )
	 */



}
