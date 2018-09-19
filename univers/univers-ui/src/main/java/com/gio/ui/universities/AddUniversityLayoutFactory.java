package com.gio.ui.universities;

import org.springframework.beans.factory.annotation.Autowired;

import com.gio.model.entity.University;
import com.gio.service.adduniversity.AddUniversityService;
import com.gio.utils.NotificationMessages;
import com.gio.utils.StudentStringUtils;
import com.gio.utils.UniversityStringUtils;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

// BUILDER PATTERN

@org.springframework.stereotype.Component
public class AddUniversityLayoutFactory {

	/*
	 * NEED to have this outside of any classes that were instantiated
	 * with the new keyword, such as AddUniversityLayout
	 * Spring dependency injection wont work otherwise
	 */
	@Autowired
	private AddUniversityService addUniversityService;
	
	
	private class AddUniversityLayout extends VerticalLayout implements Button.ClickListener {

		private TextField universityName;
		private TextField universityCountry;
		private TextField universityCity;
		private Button saveBtn;
		private University university;
		private Binder<University> fieldGroup;
		private UniversitySavedListener universitySavedListener;
		
		public AddUniversityLayout(UniversitySavedListener universitySavedListener) {
			this.universitySavedListener = universitySavedListener;
		}
		

		public AddUniversityLayout init() {
			
			fieldGroup = new BeanValidationBinder<University>(University.class);
			university = new University();
			
			universityName = new TextField(UniversityStringUtils.UNIVERSITY_NAME.getString());
			universityCountry = new TextField(UniversityStringUtils.UNIVERSITY_COUNTRY.getString());
			universityCity = new TextField(UniversityStringUtils.UNIVERSITY_CITY.getString());

			saveBtn = new Button(StudentStringUtils.SAVE_BUTTON.getString(), this);
			saveBtn.setStyleName(ValoTheme.BUTTON_FRIENDLY);

			// set null representations -- Vaadin 8 does this automatically(whew)

			return this;
		}

		public AddUniversityLayout bind() {
			
			// bind fields

			fieldGroup.forField(universityName).bind("universityName");
			fieldGroup.forField(universityCountry).bind("universityCountry");
			fieldGroup.forField(universityCity).bind("universityCity");

			fieldGroup.setBean(university);

			return this;
		}

		// Returns a component because its a Grid
		public Component layout() {
			
			setWidth("100%");
			
			GridLayout grid = new GridLayout(1,4);
			grid.setHeightUndefined();
			grid.setSpacing(true);
			
			grid.addComponent(universityName, 0,0);
			grid.addComponent(universityCountry, 0,1);
			grid.addComponent(universityCity, 0,2);
			
			grid.addComponent(saveBtn, 0,3);

			return grid;
		}

		@Override
		public void buttonClick(ClickEvent event) {
			try {
				fieldGroup.writeBean(university);
			} catch (ValidationException e) {
				Notification.show(NotificationMessages.UNIVERSITY_SAVED_VALIDATION_ERROR_TITLE.getString(),
					NotificationMessages.UNIVERSITY_SAVED_VALIDATION_ERROR_DESC.getString(), Type.ERROR_MESSAGE);
				return;
			}
			
			addUniversityService.addUniversity(university);
			universitySavedListener.universitySaved();
			clearFeilds();
			
			Notification.show(NotificationMessages.UNIVERSITY_SAVE_SUCCESS_TITLE.getString(),
					NotificationMessages.UNIVERSITY_SAVE_SUCCESS_DESC.getString(), Type.WARNING_MESSAGE);

		}

		private void clearFeilds() {
			universityName.clear();
			universityCountry.clear();
			universityCity.clear();		
		}

	}

	public Component createComponent(UniversitySavedListener universitySavedListener) {
		return new AddUniversityLayout(universitySavedListener).init().bind().layout();
	}

}
