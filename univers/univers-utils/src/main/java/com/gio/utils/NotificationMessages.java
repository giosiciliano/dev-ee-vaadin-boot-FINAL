package com.gio.utils;

public enum NotificationMessages {
	
	STUDENT_SAVE_VALIDATION_ERROR_TITLE("ERROR"),
	STUDENT_SAVE_VALIDATION_ERROR_DESC("Fields must be filled!"),
	STUDENT_SAVE_VALIDATION_SUCCESS_TITLE("SUCCESS"),
	STUDENT_SAVE_VALIDATION_SUCCESS_DESC("Student has been saved successfully!"),
	STUDENT_REMOVE_BUTTON("Remove"), 
	STUDENT_REMOVE_SUCCESS_TITLE("REMOVE"), 
	STUDENT_REMOVE_SUCCESS_DESC("Student(s) successfully removed!"),
	UNIVERSITY_SAVED_VALIDATION_ERROR_TITLE("ERROR"), 
	UNIVERSITY_SAVED_VALIDATION_ERROR_DESC("Fields must be filled!"),
	UNIVERSITY_SAVE_SUCCESS_TITLE("SAVE"),
	UNIVERSITY_SAVE_SUCCESS_DESC("University saved successfully!"), 
	STUDENT_SAVE_INVALID_TITLE("ERROR"), 
	STUDENT_SAVE_INVALID_DESC("Must have at least 1 University!"),
	
	
	
	;
	
	private final String string;
	
	private NotificationMessages(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}

}
