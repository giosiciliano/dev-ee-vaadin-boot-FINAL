package com.gio.ui.universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gio.model.entity.Student;
import com.gio.model.entity.University;
import com.gio.service.showalluniversities.ShowAllUniversitiesService;
import com.gio.ui.commons.UIComponentBuilder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowUniversitiesLayoutFactory implements UIComponentBuilder {

	private List<University> universities;
	private ListDataProvider<University> container;

	@Autowired
	private ShowAllUniversitiesService showAllUniverstiesService;

	private Grid<University> universityTable;

	private class ShowUniversitiesLayout extends VerticalLayout {

		public ShowUniversitiesLayout init() {

			setMargin(true);

			// create and set the grid
			universityTable = new Grid<>();

			// add only the columns you would like to see
			refreshTable();

			return this;
		}

		public ShowUniversitiesLayout layout() {
			addComponent(universityTable);
			return this;
		}

	}

	public void refreshTable() {
		universityTable.removeAllColumns();
		universities = showAllUniverstiesService.getAllUniversities();
		universityTable.setItems(universities);
		universityTable.addColumn(University::getUniversityName).setCaption("University Name");
		universityTable.addColumn(University::getUniversityCountry).setCaption("University Country");
		universityTable.addColumn(University::getUniversityCity).setCaption("University City");

	}

	@Override
	public Component createComponent() {
		return new ShowUniversitiesLayout().init().layout();
	}

}
