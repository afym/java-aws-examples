package com.local.pe.dynamo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.local.pe.base.CredentialBuilder;

public class DynamoConnect {

	public void createTable() {
		AWSCredentials credential = CredentialBuilder.getCredential();
		AmazonDynamoDBClient awsDynamo = new AmazonDynamoDBClient(credential);
		awsDynamo.createTable(this.getTableRequest());
	}

	private CreateTableRequest getTableRequest(){
		CreateTableRequest table = new CreateTableRequest();
		//table.withTableName("")
		return table;
	}
}
