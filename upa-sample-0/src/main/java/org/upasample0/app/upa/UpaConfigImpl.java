package org.upasample0.app.upa;

import java.util.Arrays;
import java.util.List;

import org.upasample0.app.controllers.JobController;
import org.upasample0.app.controllers.SearchController;
import org.upasample0.app.controllers.UserController;
import org.upasample0.upa.UpaConfig;

public class UpaConfigImpl implements UpaConfig {

	@Override
	public List<Class<?>> getControllers() {
		return Arrays.asList(JobController.class, SearchController.class, UserController.class);
	}

}
