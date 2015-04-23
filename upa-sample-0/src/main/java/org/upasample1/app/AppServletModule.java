package org.upasample1.app;

import org.upasample1.upa.UpaAutoServlet;

import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {
	@Override
	protected void configureServlets() {
		serve("/upa/*").with(UpaAutoServlet.class);
	}
}
