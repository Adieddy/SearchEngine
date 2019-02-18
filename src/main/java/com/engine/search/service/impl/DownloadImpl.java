package com.engine.search.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.engine.search.model.Data;
import com.engine.search.service.Download;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DownloadImpl implements Download{
	
	public Map<Integer, Data> getDefaultData(String url) throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Data> tempList = mapper.readValue(new URL(url), new TypeReference<List<Data>>() {});
		return tempList.stream().collect(Collectors.toMap(Data::getId, data->data));
	}
}
