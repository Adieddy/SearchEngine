package com.engine.search.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.engine.search.model.Data;
import com.engine.search.service.CommentService;
import com.engine.search.service.Download;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@EnableAutoConfiguration
public class SearchController {
	
	@Autowired
	Download download;
	
	@Autowired
	CommentService commentService;
	
	private Map<Integer, Data> inputData;
	
	@RequestMapping(value = "/", method= RequestMethod.GET)
	public String initializeData(@Value("${data.url}") String url) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		inputData = download.getDefaultData(url);
		return "home";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Data> getSearchedData(@RequestParam String searchIdentifier){
		return commentService.getSearchedData(inputData, searchIdentifier);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public @ResponseBody String deleteData(@RequestParam Integer deleteIdentifier){
		commentService.removeData(inputData, deleteIdentifier);
		return "Success";
	}
}
