package com.gurushi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	Neo4jTemplate template;
	
	@Override
	public void cleanUpDb() {
    	
    	String cypherForClearingGraph = 
    			"START n=node(*)"
    			+ " MATCH n-[r?]-()"
    			+ " WHERE ID(n) <> 0"		
    			+ " DELETE n,r";
    	
    	template.query(cypherForClearingGraph, null);
    }

}
