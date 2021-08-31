package org.example.www.tosca.nodetypeimplementations;

import java.io.IOException;
import java.util.HashMap;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import org.eclipse.winery.generators.ia.jaxws.AbstractService;

@WebService(targetNamespace = "http://nodetypeimplementations.tosca.www.example.org/")
public class org_example_www_tosca_nodetypes_OpenWhiskPlatform__org_opentosca_interfaces_lifecycle extends AbstractService {

    private static final Logger logger = LoggerFactory.getLogger(
            org_example_www_tosca_nodetypes_OpenWhiskPlatform__org_opentosca_interfaces_lifecycle.class
    );

	@WebMethod
	@SOAPBinding
	@Oneway
	public void install(
		@WebParam(name="function_name", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String function_name,
		@WebParam(name="runtime", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String runtime,
		@WebParam(name="api_host", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String api_host,
		@WebParam(name="auth_key", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String auth_key,
		@WebParam(name="namespace", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String namespace
	) throws ClientProtocolException, IOException {
		// TODO: Implement your operation here.

		// This HashMap holds the return parameters of this operation.
		final HashMap<String,String> returnParameters = new HashMap<String, String>();

		// create URL for OpenWhisk REST Api
		final String functionRequestUrl  = "https://" + auth_key + "@" + api_host 
            + "/api/v1/namespaces/" + namespace + "/actions/" + function_name + "?overwrite=true";

		// create body
		final String bodyFunction = "{\"namespace\":\"" + namespace + "\", \"name\":\"" + function_name +
             "\", \"exec\":{\"kind\":\"" + runtime + "\", \"code\":\"test\"}}";
        final StringEntity entity = new StringEntity(bodyFunction, ContentType.APPLICATION_JSON);
		
		// build http client and request
		final HttpClient httpClient = HttpClientBuilder.create().build(); 
		final HttpPut requestFunction = new HttpPut(functionRequestUrl );
		
		// set body and header
		requestFunction.setEntity(entity);
		requestFunction.setHeader("Accept", "application/json");
		
		// execute the request
		final HttpResponse response = httpClient.execute(requestFunction);
		// System.out.println("Status-code Function->" + response.getStatusLine().getStatusCode());
		sendResponse(returnParameters);
	}

	@WebMethod
	@SOAPBinding
	@Oneway
	public void configure(
		@WebParam(name="event_name", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String event_name,
		@WebParam(name="event_type", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String event_type,
		@WebParam(name="function_name", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String function_name,
		@WebParam(name="rule_name", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String rule_name,
		@WebParam(name="binding", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String binding,
		@WebParam(name="api_host", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String api_host,
		@WebParam(name="auth_key", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String auth_key,
		@WebParam(name="namespace", targetNamespace="http://nodetypeimplementations.tosca.www.example.org/") String namespace
	) throws ClientProtocolException, IOException {
		// TODO: Implement your operation here.

		// This HashMap holds the return parameters of this operation.
		final HashMap<String,String> returnParameters = new HashMap<String, String>();

		// create URL for OpenWhisk REST Api Event
		final String requestUrlEvent = "https://" + auth_key + "@" + api_host + "/api/v1/namespaces/" + namespace + "/triggers/" + event_name + "?overwrite=true";
		
		// create body for Event
		final String bodyEvent = "{\"name\":\"" + event_name +"\",\"annotations\":[{\"key\":\"" + event_type + "\",\"value\": \"" + binding + "\"}]}";
		final StringEntity entityEvent = new StringEntity(bodyEvent, ContentType.APPLICATION_JSON);
		
		// build http client and request for Event
		final HttpClient httpClient = HttpClientBuilder.create().build(); 
		final HttpPut requestEvent = new HttpPut(requestUrlEvent);
		
		// set body and header for Event
		requestEvent.setEntity(entityEvent);
		requestEvent.setHeader("Accept", "application/json");
		
		// execute request for Event
		final HttpResponse responseEvent = httpClient.execute(requestEvent);
		System.out.println("Status-code Event->" + responseEvent.getStatusLine().getStatusCode());
		
		
		// create URL for OpenWhisk REST Api for Rule
		final String requestUrlRule = "https://" + auth_key + "@" + api_host + "/api/v1/namespaces/" + namespace + "/rules/" + rule_name + "?overwrite=true";
						
		// create body for Rule
		final String bodyRule = "{\"name\":\"" + rule_name + "\", \"status\":\"active\", \"trigger\":\"/" + namespace + "/" + event_name + "\", \"action\":\"/" + namespace + "/" + function_name + "\"}";
		final StringEntity entityRule = new StringEntity(bodyRule, ContentType.APPLICATION_JSON);
		
		// build http client and request for Rule
		final HttpClient httpClient2 = HttpClientBuilder.create().build(); 
		final HttpPut requestRule = new HttpPut(requestUrlRule);
		
		// set body and header for Rule
		requestRule.setEntity(entityRule);
		requestRule.setHeader("Accept", "application/json");
		
		final HttpResponse responseRule = httpClient2.execute(requestRule);
		// System.out.println("Status-code Rule->" + responseRule.getStatusLine().getStatusCode());
		sendResponse(returnParameters);
	}
	/*
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		
		String function_name = "function-test";
		String runtime = "nodejs:12";
		String api_host = "eu-gb.functions.cloud.ibm.com";
		String auth_key = "989acf75-2b69-40a2-8514-5941a588404c:VNfLHc4JI36h03BILhCsAAr7uNBXXdjflWhkJy49V5txKJUSVPHZJf3aFpKaky5G";
		String namespace = "pascal.abotsitse@web.de_dev";
		
		org_example_www_tosca_nodetypes_OpenWhiskPlatform__org_opentosca_interfaces_lifecycle test = new org_example_www_tosca_nodetypes_OpenWhiskPlatform__org_opentosca_interfaces_lifecycle();
		test.install(function_name, runtime, api_host, auth_key, namespace);
		
		String event_name = "trigger-test";
		String binding = "/pascal.abotsitse@web.de_dev/cloudant-service-binding/changes";
		String event_type = "feed";
		String rule_name = "rule-test";
		
		test.configure(event_name, event_type, function_name, rule_name, binding, api_host, auth_key, namespace);
	*/
	}



}
