package com.agan.springboot_es.service;

/**
 * @author cg
 */
public interface EsService {

    public void saveValue();

    public String getValue(String id);

    public String deleteValue(String id);
}
