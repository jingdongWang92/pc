package com.jcble.jcparking.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLConverUtil {
	private static Map<Class<?>, Unmarshaller> uMap = new HashMap();
	private static Map<Class<?>, Marshaller> mMap = new HashMap();

	public static <T> T convertToObject(Class<T> clazz, String xml) {
		return convertToObject(clazz, new StringReader(xml));
	}

	public static <T> T convertToObject(Class<T> clazz, InputStream inputStream) {
		return convertToObject(clazz, new InputStreamReader(inputStream));
	}

	public static <T> T convertToObject(Class<T> clazz, Reader reader) {
		try {
			if (!uMap.containsKey(clazz)) {
				JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { clazz });
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				uMap.put(clazz, unmarshaller);
			}
			return (T) ((Unmarshaller) uMap.get(clazz)).unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertToXML(Object object) {
		try {
			if (!mMap.containsKey(object.getClass())) {
				JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { object.getClass() });
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
				marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
				mMap.put(object.getClass(), marshaller);
			}
			StringWriter stringWriter = new StringWriter();
			((Marshaller) mMap.get(object.getClass())).marshal(object, stringWriter);
			return stringWriter.getBuffer().toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
