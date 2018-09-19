package com.gio.ui.commons;

import com.gio.ui.universities.UniversityLayoutFactory;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class UniversLogoLayoutFactory implements UIComponentBuilder {

	private class LogoLayout extends VerticalLayout {
		
		private Embedded logo;
		
		public LogoLayout init() {
			
			logo = new Embedded();
			logo.setSource(new ThemeResource("../../images/vaadin.jpg"));
			logo.setWidth("450px");
			logo.setHeight("250px");
			
			return this;
		}
		
		public LogoLayout layout() {
			addComponent(logo);
			setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
			return this;
		}
		
	}
	
	public Component createComponent() {
		return new LogoLayout().init().layout();
	}

}
