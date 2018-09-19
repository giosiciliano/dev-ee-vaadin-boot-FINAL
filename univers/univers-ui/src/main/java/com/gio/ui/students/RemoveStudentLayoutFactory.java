package com.gio.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gio.model.entity.Student;
import com.gio.service.removestudent.RemoveStudentService;
import com.gio.service.showallstudents.ShowAllStudentsService;
import com.gio.ui.commons.UniversMainUI;
import com.gio.utils.NotificationMessages;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name=RemoveStudentLayoutFactory.NAME, ui=UniversMainUI.class)
public class RemoveStudentLayoutFactory extends VerticalLayout implements View, Button.ClickListener {

	public static final String NAME = "removestudent";
	private Grid<Student> removeStudentsTable;
	private Button removeStudentsButton;
	private List<Student> students;
	
	@Autowired
	private ShowAllStudentsService showAllStudentsService;
	
	@Autowired
	private RemoveStudentService removeStudentsService;
	
	ListDataProvider<Student> dataProvider;
	
	private void addLayout() {
		
		removeStudentsButton = new Button(NotificationMessages.STUDENT_REMOVE_BUTTON.getString());
		
		setMargin(true);
		
		
		removeStudentsTable = new Grid<>();
		refreshTable();
		dataProvider = new ListDataProvider<Student>(students);
		removeStudentsTable.setDataProvider(dataProvider);
		removeStudentsTable.setSelectionMode(SelectionMode.MULTI);
		
		removeStudentsButton.addClickListener(this);
		removeStudentsButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		addComponent(removeStudentsTable);
		addComponent(removeStudentsButton);
		
	}
	
	private void loadStudents() {
		students = showAllStudentsService.getAllStudents();
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		MultiSelectionModel selectionModel = (MultiSelectionModel) removeStudentsTable.getSelectionModel();
		for(Object selectedItem : selectionModel.getSelectedItems()) {
			Student student = (Student) selectedItem;
			dataProvider.getItems().remove(student);
			removeStudentsService.removeStudent(student);
			dataProvider.refreshAll();
		}

		Notification.show(NotificationMessages.STUDENT_REMOVE_SUCCESS_TITLE.getString(),
				NotificationMessages.STUDENT_REMOVE_SUCCESS_DESC.getString(), Type.WARNING_MESSAGE);
	}
	
	public void enter(ViewChangeEvent event) {
		
		if (removeStudentsTable != null) return;
		
		loadStudents();
		addLayout();
		
	}

	public void refreshTable() {
		removeStudentsTable.removeAllColumns();
		loadStudents();
		removeStudentsTable.setItems(students);
		removeStudentsTable.addColumn(Student::getFirstName).setCaption("First Name");
		removeStudentsTable.addColumn(Student::getLastName).setCaption("Last Name");
		removeStudentsTable.addColumn(Student::getAge).setCaption("Age");
		removeStudentsTable.addColumn(Student::getGender).setCaption("Gender");
	}
	
}
