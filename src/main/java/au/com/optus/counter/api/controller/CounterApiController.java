package au.com.optus.counter.api.controller;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.optus.counter.api.models.SearchRequest;
import au.com.optus.counter.api.models.SearchResponse;
import au.com.optus.counter.api.processor.CounterApiProcessor;

@RestController
@RequestMapping
public class CounterApiController {

	@Autowired
	CounterApiProcessor counterApiProcessor;
	
	@PostMapping("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SearchResponse stringCount(@RequestBody SearchRequest request) throws IOException {
		return counterApiProcessor.stringCountProcess(request);
	}
    
	@GetMapping("/top/{val}")
	@Produces({"text/csv"})
	public String topCount(@PathVariable String val) throws IOException {
		return counterApiProcessor.topCountProcess(val);
	}
}
