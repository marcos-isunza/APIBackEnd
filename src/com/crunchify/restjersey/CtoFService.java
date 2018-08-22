package com.crunchify.restjersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import org.json.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
 
@Path("/process")
public class CtoFService {
	
	@Path("minmax/{numbers}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findMinMax(@PathParam("numbers") String numbers) {
		
		String json = "";
		Response response = null;
		
		System.out.println(numbers);

		List<String> numbersString = Arrays.asList(numbers.split(","));
		
		ArrayList<Integer> numbersList = new ArrayList<Integer>();
		
		for (int i=0; i<numbersString.size();i++) {
			
			numbersList.add(Integer.parseInt(numbersString.get(i)));
		}

		int min=Integer.parseInt(Collections.min(numbersString));
		int max=Integer.parseInt(Collections.max(numbersString));
 
		String result = "Max: "+ max+" & Min: " + min;
		
		MinMaxBean resultJson = new MinMaxBean();
		resultJson.setMin(min);
		resultJson.setMax(max);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			 json = ow.writeValueAsString(resultJson);
			 if(json != null)
			 {
				 response = Response.status(Status.OK).entity(json).build();
			 }
			 else
				 response = Response.status(Status.BAD_REQUEST).entity(json).build();
			 
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@Path("ctof")
	@GET
	@Produces("application/xml")
	public String convertCtoF() {
 
		Double fahrenheit;
		Double celsius = 36.8;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
 
	@Path("{c}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("c") Double c) {
		Double fahrenheit;
		Double celsius = c;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
}