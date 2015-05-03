package com.fakecompany.samplewebapp;

import org.upa.UpaServlet;

import com.fakecompany.samplewebapp.controllers.BasicTypesController;
import com.fakecompany.samplewebapp.controllers.ComplexTypesController;
import com.fakecompany.samplewebapp.controllers.FileController;
import com.google.inject.servlet.ServletModule;

public class WebAppModule extends ServletModule {
	@Override
	protected void configureServlets() {
		bind(FileController.class);
		bind(BasicTypesController.class);
		bind(ComplexTypesController.class);
		serve("/upa/*").with(UpaServlet.class);
	}

}
