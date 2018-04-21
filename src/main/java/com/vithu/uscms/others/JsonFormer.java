package com.vithu.uscms.others;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.json.JSONException;

import com.vithu.uscms.session.CurrentUser;

/**
* @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose User Entity Class
 */

public class JsonFormer {
	public static String form(GenericResult resultObj) throws JSONException
	{
	    String resultJson = "{ status: false }";
            try
            {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(Feature.INDENT_OUTPUT);
                mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
                ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();   
                resultJson = ow.writeValueAsString( resultObj );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
            
            return resultJson;
	}
	
	public static CurrentUser deForm(String objString) {
		ObjectMapper mapper = new ObjectMapper();
		CurrentUser curruser = new CurrentUser();
		try {
			curruser = mapper.readValue(objString, CurrentUser.class);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return curruser;
		
	}

	public static String form(CurrentUser currUser) throws JSONException{
		  String resultJson = "{ status: false }";
          try {
              ObjectMapper mapper = new ObjectMapper();
              mapper.enable(Feature.INDENT_OUTPUT);
              mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
              ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();   
              resultJson = ow.writeValueAsString( currUser );
          }
          catch ( Exception e ) {
              e.printStackTrace();
          }
          return resultJson;
	}
}
