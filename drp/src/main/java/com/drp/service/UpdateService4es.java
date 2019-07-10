package com.drp.service;

import java.io.IOException;

public interface UpdateService4es {

	void create4es(String[] create, Integer c) throws IOException;

	void update4es(String[] update, Integer u) throws IOException;

	void delete4es(String[] delete, Integer d) throws IOException;

}
