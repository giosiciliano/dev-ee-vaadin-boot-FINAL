package com.gio.ui.universities;

import org.springframework.beans.factory.annotation.Autowired;

import com.gio.ui.commons.UniversMainUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView(name=UniversityLayoutFactory.NAME, ui=UniversMainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View, UniversitySavedListener {

	public static final String NAME = "operations";
	
	private TabSheet tabSheet;
	
	@Autowired
	private AddUniversityLayoutFactory addUniversityLayoutFactory;
	
	@Autowired
	private ShowUniversitiesLayoutFactory showUniversitiesLayoutFactory;
	
	@Autowired
	private StatisticsUniversityLayoutFactory statUniversityLayoutFactory;
	
	private void addLayout() {
		setMargin(true);
		
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");

		// adding listener to addUniversityLayoutFactory to refresh the showUniversitiesLayoutFactory
		Component addUniversityTab = addUniversityLayoutFactory.createComponent(this);
		Component showAllUniversitieTab = showUniversitiesLayoutFactory.createComponent();
		Component showStatisticsTab = statUniversityLayoutFactory.createComponent();
		
		tabSheet.addTab(addUniversityTab, "ADD UNIVERSITY");
		tabSheet.addTab(showAllUniversitieTab, "SHOW ALL UNIVERSITIES");
		tabSheet.addTab(showStatisticsTab, "STATISTICS");

		addComponent(tabSheet);
	}
	
	@Override
	public void universitySaved() {
		showUniversitiesLayoutFactory.refreshTable();
		statUniversityLayoutFactory.refresh();
	}
	
	
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}


	


}