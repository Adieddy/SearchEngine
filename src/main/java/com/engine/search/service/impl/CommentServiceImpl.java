package com.engine.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.engine.search.model.Data;
import com.engine.search.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	public List<Data> getSearchedData(Map<Integer, Data> wholeData, String searchIdentifier){
		List<Data> output = new ArrayList<>();
		long start = System.currentTimeMillis();
		wholeData.forEach((k,v)->{
			if(v.getBody().contains(searchIdentifier)){
				output.add(v);
			}
		});
		long end = System.currentTimeMillis();
		System.out.println("Time taken to search: "+(end-start));
		return output;
	}
	
	public void removeData(Map<Integer, Data> inputMap, Integer identifier){
		inputMap.remove(identifier);
	}
}
