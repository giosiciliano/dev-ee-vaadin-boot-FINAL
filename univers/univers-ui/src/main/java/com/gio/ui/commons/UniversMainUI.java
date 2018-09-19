package com.gio.ui.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.gio.navigator.UniversNavigator;
import com.gio.ui.students.StudentLayoutFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

// BUILDER PATTERN

@SpringUI(path=UniversMainUI.NAME)
@Title("U n i v e r s")
@Theme("valo")
public class UniversMainUI extends UI {

	public static final String NAME = "/ui";
	
	private Panel tabsPanel = new Panel();
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private UniversLogoLayoutFactory universLogoLayoutFactory;
	
	@Autowired
	private UniversMenuFactory universMenuFactory;
	
	@Autowired
	private SpringViewProvider viewProvider;
	
	
	@Override
	protected void init(VaadinRequest request) {
		
		VerticalLayout root = new VerticalLayout();
		
		tabsPanel.setHeight("100%");
		
		root.setSizeFull();
		root.setMargin(true);
		
		Panel contentPanel = new Panel();
		contentPanel.setWidth("75%");
		contentPanel.setHeight("100%");
		
		Panel logoPanel = new Panel();
		logoPanel.setWidth("75%");
		logoPanel.setHeightUndefined();
		
		HorizontalLayout uiLayout = new HorizontalLayout();
		uiLayout.setSizeFull();
		uiLayout.setMargin(true);
		
		Component logo = universLogoLayoutFactory.createComponent();
		Component menu = universMenuFactory.createComponent();
		
		uiLayout.addComponent(menu);
		uiLayout.addComponent(tabsPanel);
		
		uiLayout.setComponentAlignment(tabsPanel, Alignment.TOP_CENTER);
		uiLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
		
		uiLayout.setExpandRatio(menu, 1);
		uiLayout.setExpandRatio(tabsPanel, 2);
		
		logoPanel.setContent(logo);
		contentPanel.setContent(uiLayout);
		
		root.addComponent(logoPanel);
		root.addComponent(contentPanel);
		root.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
		root.setComponentAlignment(logoPanel, Alignment.TOP_CENTER);
		root.setExpandRatio(contentPanel, 1);
		
		initNavigator();
		
		setContent(root);

	}


	private void initNavigator() {
		UniversNavigator navigator = new UniversNavigator(this, tabsPanel);
		appContext.getAutowireCapableBeanFactory().autowireBean(navigator);
		navigator.addProvider(viewProvider);
		navigator.navigateTo(StudentLayoutFactory.NAME);
	}

}
