package com.markweb.objects;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class AdventureFormBean {
	
	@NotBlank(message = "Must Choose an Option")
	String selectedOption;
	List<String> options;
	
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	
}
