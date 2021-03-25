package com.vadim.store.utility;

import java.net.URL;
import java.util.Collections;
import java.util.Map;

public class MapHandler {

    public Map<String, String> getMapFromXml(String fileName) {
        URL resource = XmlReader.class.getClassLoader().getResource(fileName);
        return new XmlReader().getSortConditions(resource.getPath());
    }

    public Map<String, String> createSingletonMap(String sortKey, String sortValue) {
        return Collections.singletonMap(sortKey, sortValue);
    }
}
