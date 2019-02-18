package com.engine.search.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import com.engine.search.model.Data;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface Download {
	public Map<Integer, Data> getDefaultData(String url) throws JsonParseException, JsonMappingException, MalformedURLException, IOException;
}
