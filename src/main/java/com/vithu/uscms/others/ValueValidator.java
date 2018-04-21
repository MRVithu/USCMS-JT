package com.vithu.uscms.others;


/**
 * @author S.Sugashan
 * @CreatedOn 26th December 2017
 * Method that validates plain text is it is empty or null
 * @param value: Value to be validated
 * @param label: Label to show which field is being validated
 * @return: Generic result with message and status
 * @purpose: validate inputs.
 */

public class ValueValidator {
	
	
	
	public static GenericResult validateText(String value, String label){
		GenericResult result = new GenericResult();
		
		if(value == null || "".equals(value)){
			result.setMessage(label + " " + MessageConstant.MSG_EMPTY);
			result.setStatus(false);
		}
		else{
			result.setMessage("");
			result.setStatus(true);
		}
		return result;
	}

	public static GenericResult validateTextForNumbers(String value, String label) {
		GenericResult result = new GenericResult();
		
		if(value != null && value.matches("\\d+")){
			result.setMessage(label + " " + MessageConstant.MSG_NOT_NUMBER);
			result.setStatus(true);
		}
		else{
			result.setMessage("");
			result.setStatus(false);
		}
		return result;
	}
}
