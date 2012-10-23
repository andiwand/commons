package at.andiwand.commons.lwxml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import at.andiwand.commons.lwxml.path.LWXMLNodeIdentifier;
import at.andiwand.commons.lwxml.path.LWXMLPath;
import at.andiwand.commons.lwxml.reader.LWXMLBranchReader;
import at.andiwand.commons.lwxml.reader.LWXMLElementReader;
import at.andiwand.commons.lwxml.reader.LWXMLReader;
import at.andiwand.commons.lwxml.reader.LWXMLStreamReader;


public class LWXMLUtil {
	
	public static void flush(LWXMLReader in) throws IOException {
		while (in.readEvent() != LWXMLEvent.END_DOCUMENT);
	}
	
	public static void flushBranch(LWXMLReader in) throws IOException {
		flush(new LWXMLBranchReader(in));
	}
	
	public static void flushElement(LWXMLReader in) throws IOException {
		flush(new LWXMLElementReader(in));
	}
	
	public static void flushUntilPath(InputStream in, LWXMLPath path)
			throws IOException {
		flushUntilPath(new LWXMLStreamReader(in), path);
	}
	
	public static void flushUntilPath(Reader in, LWXMLPath path)
			throws IOException {
		flushUntilPath(new LWXMLStreamReader(in), path);
	}
	
	public static void flushUntilPath(LWXMLReader in, LWXMLPath path)
			throws IOException {
		int depth = 0;
		int matchingIndex = 0;
		int nodeIndex = 0;
		
		while (true) {
			LWXMLEvent event = in.readEvent();
			if (event == LWXMLEvent.END_DOCUMENT) return;
			
			switch (event) {
			case START_ELEMENT:
				depth++;
				if (depth > (matchingIndex + 1)) break;
				
				LWXMLNodeIdentifier nodeIdentifier = path
						.getNodeIdentifier(matchingIndex);
				if (!in.readValue().equals(nodeIdentifier.getElementName()))
					break;
				
				if (nodeIndex < nodeIdentifier.getIndex()) {
					nodeIndex++;
				} else {
					matchingIndex++;
					nodeIndex = 0;
					
					if (matchingIndex >= path.getDepth()) return;
				}
				
				break;
			case END_EMPTY_ELEMENT:
			case END_ELEMENT:
				depth--;
				if (matchingIndex > depth) return;
				
				break;
			}
		}
	}
	
	public static LWXMLBranchReader getBranchReader(InputStream in,
			LWXMLPath path) throws IOException {
		return getBranchReader(new LWXMLStreamReader(in), path);
	}
	
	public static LWXMLBranchReader getBranchReader(Reader in, LWXMLPath path)
			throws IOException {
		return getBranchReader(new LWXMLStreamReader(in), path);
	}
	
	public static LWXMLBranchReader getBranchReader(LWXMLReader in,
			LWXMLPath path) throws IOException {
		flushUntilPath(in, path);
		return new LWXMLBranchReader(in);
	}
	
	public static String getAttributeValue(InputStream in, LWXMLPath path,
			String attributeName) throws IOException {
		return getAttributeValue(new LWXMLStreamReader(in), path, attributeName);
	}
	
	public static String getAttributeValue(Reader in, LWXMLPath path,
			String attributeName) throws IOException {
		return getAttributeValue(new LWXMLStreamReader(in), path, attributeName);
	}
	
	public static String getAttributeValue(LWXMLReader in, LWXMLPath path,
			String attributeName) throws IOException {
		flushUntilPath(in, path);
		
		while (true) {
			LWXMLEvent event = in.readEvent();
			
			switch (event) {
			case ATTRIBUTE_NAME:
				if (!in.readValue().equals(attributeName)) continue;
				return in.readFollowingValue();
			case ATTRIBUTE_VALUE:
				break;
			case END_ATTRIBUTE_LIST:
				return null;
			}
		}
	}
	
	public static String getFirstAttributeValue(InputStream in,
			String attributeName) throws IOException {
		return getFirstAttributeValue(new LWXMLStreamReader(in), attributeName);
	}
	
	public static String getFirstAttributeValue(Reader in, String attributeName)
			throws IOException {
		return getFirstAttributeValue(new LWXMLStreamReader(in), attributeName);
	}
	
	public static String getFirstAttributeValue(LWXMLReader in,
			String attributeName) throws IOException {
		while (true) {
			LWXMLEvent event = in.readEvent();
			
			switch (event) {
			case ATTRIBUTE_NAME:
				if (!in.readValue().equals(attributeName)) break;
				return in.readFollowingValue();
			case END_DOCUMENT:
				return null;
			}
		}
	}
	
	public static List<String> getAllAttributeValue(InputStream in,
			String attributeName) throws IOException {
		return getAllAttributeValue(new LWXMLStreamReader(in), attributeName);
	}
	
	public static List<String> getAllAttributeValue(Reader in,
			String attributeName) throws IOException {
		return getAllAttributeValue(new LWXMLStreamReader(in), attributeName);
	}
	
	public static List<String> getAllAttributeValue(LWXMLReader in,
			String attributeName) throws IOException {
		List<String> result = new LinkedList<String>();
		
		while (true) {
			LWXMLEvent event = in.readEvent();
			
			switch (event) {
			case ATTRIBUTE_NAME:
				if (!in.readValue().equals(attributeName))
					result.add(in.readFollowingValue());
				break;
			case END_DOCUMENT:
				return result;
			}
		}
	}
	
	public static Map<String, String> parseAllAttributes(LWXMLReader in)
			throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		
		while (true) {
			LWXMLEvent event = in.readEvent();
			
			switch (event) {
			case ATTRIBUTE_NAME:
				result.put(in.readValue(), in.readFollowingValue());
				break;
			case END_ATTRIBUTE_LIST:
				return result;
			default:
				throw new LWXMLIllegalEventException(event);
			}
		}
	}
	
	public static Map<String, String> parseAttributes(LWXMLReader in,
			Set<String> attributeNames) throws IOException {
		if (attributeNames.isEmpty()) return Collections.emptyMap();
		
		Map<String, String> result = new HashMap<String, String>(attributeNames
				.size());
		
		while (true) {
			LWXMLEvent event = in.readEvent();
			
			switch (event) {
			case ATTRIBUTE_NAME:
				String attributeName = in.readValue();
				if (attributeNames.contains(attributeName)) {
					result.put(attributeName, in.readFollowingValue());
					if (result.size() >= attributeNames.size()) return result;
				}
			case ATTRIBUTE_VALUE:
				break;
			case END_ATTRIBUTE_LIST:
				return result;
			default:
				throw new LWXMLIllegalEventException(event);
			}
		}
	}
	
	public static String parseSingleAttributes(LWXMLReader in,
			String attributeName) throws IOException {
		while (true) {
			LWXMLEvent event = in.readEvent();
			
			switch (event) {
			case ATTRIBUTE_NAME:
				if (in.readValue().equals(attributeName))
					return in.readFollowingValue();
				break;
			case ATTRIBUTE_VALUE:
				break;
			case END_ATTRIBUTE_LIST:
				return null;
			default:
				throw new LWXMLIllegalEventException(event);
			}
		}
	}
	
	public static void flushEmptyElement(LWXMLReader in) throws IOException {
		LWXMLEvent event = in.readEvent();
		if (event == LWXMLEvent.START_ELEMENT) event = in.readEvent();
		
		while (true) {
			switch (event) {
			case ATTRIBUTE_NAME:
			case ATTRIBUTE_VALUE:
			case END_ATTRIBUTE_LIST:
				break;
			case END_EMPTY_ELEMENT:
			case END_ELEMENT:
				return;
			default:
				throw new LWXMLIllegalEventException(event);
			}
			
			event = in.readEvent();
		}
	}
	
	public static void flushStartElement(LWXMLReader in) throws IOException {
		LWXMLEvent event = in.readEvent();
		if (event == LWXMLEvent.START_ELEMENT) event = in.readEvent();
		
		while (true) {
			switch (event) {
			case ATTRIBUTE_NAME:
			case ATTRIBUTE_VALUE:
				break;
			case END_ATTRIBUTE_LIST:
				return;
			default:
				throw new LWXMLIllegalEventException(event);
			}
			
			event = in.readEvent();
		}
	}
	
	public static void flushUntilEventValue(LWXMLReader in, LWXMLEvent event,
			String value) throws IOException {
		if (!event.hasValue()) throw new LWXMLIllegalEventException(event);
		
		while (true) {
			LWXMLEvent currentEvent = in.readEvent();
			if (currentEvent == LWXMLEvent.END_DOCUMENT)
				throw new LWXMLIllegalEventException(event);
			
			if ((currentEvent == event) && in.readValue().equals(value))
				return;
		}
	}
	
	public static void flushUntilStartElement(LWXMLReader in,
			String startElement) throws IOException {
		flushUntilEventValue(in, LWXMLEvent.START_ELEMENT, startElement);
	}
	
	public static void flushUntilEndElement(LWXMLReader in, String endElement)
			throws IOException {
		flushUntilEventValue(in, LWXMLEvent.END_ELEMENT, endElement);
	}
	
	public static boolean isEmptyElement(LWXMLReader in) throws IOException {
		if (in.getCurrentEvent() != LWXMLEvent.END_ATTRIBUTE_LIST)
			flushStartElement(in);
		
		switch (in.readEvent()) {
		case END_EMPTY_ELEMENT:
		case END_ELEMENT:
			return true;
		}
		
		return false;
	}
	
	private LWXMLUtil() {}
	
}