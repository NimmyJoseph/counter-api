package au.com.optus.counter.api.controller;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.optus.counter.api.exception.CustomException;
import au.com.optus.counter.api.model.SearchRequest;
import au.com.optus.counter.api.model.SearchResponse;
import au.com.optus.counter.api.service.CounterApiService;

@RestController
@RequestMapping
public class CounterApiController {

	@Autowired
	CounterApiService counterApiService;
	
	@PostMapping("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<Object> stringCount(@RequestBody SearchRequest request) throws IOException {
		SearchResponse searchResponse = counterApiService.stringCountProcess(request);
		ResponseEntity<Object> res =  new ResponseEntity<Object>(searchResponse, HttpStatus.OK);
		return res;
	}
    
	@GetMapping("/top/{val}")
	@Produces({"text/csv"})
	public ResponseEntity<Object> topCount(@PathVariable String val) throws IOException {
		String str = counterApiService.topCountProcess(val);
		ResponseEntity<Object> res =  new ResponseEntity<Object>(str, HttpStatus.OK);
		return res;
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        CustomException custEx = new CustomException();
        custEx.setErrorCode("fault");
        custEx.setErrorMessage(ex.getMessage().trim());
		return new ResponseEntity<Object>(custEx, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
