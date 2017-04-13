package com.pk.configuration;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SessionRoutingDataSource extends AbstractRoutingDataSource {

	private String clientKey;

	private Map<String, String> saasMap;

	@Override
	protected Object determineCurrentLookupKey() {
		// log.info("------------- determineCurrentLookupKey" + getClientKey());

		if (getClientKey() == null) {
			return null;
		}
		return getClientKey();
	}

	@Qualifier("dataSources")
	public void setDataSources(Map<String, DataSource> dataSources) {
		log.info("Datasource Map : " + dataSources);
		setDataSources(dataSources);
	}

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public Map<String, String> getSaasMap() {
		return saasMap;
	}

	public void setSaasMap(Map<String, String> saasMap) {
		this.saasMap = saasMap;
	}

	
}
