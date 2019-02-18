package com.engine.search.service;

import java.util.List;
import java.util.Map;

import com.engine.search.model.Data;

public interface CommentService {
	public List<Data> getSearchedData(Map<Integer, Data> wholeData, String searchIdentifier);
	public void removeData(Map<Integer, Data> inputMap, Integer identifier);
}
