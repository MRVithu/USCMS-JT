package com.vithu.uscms.others;


import javax.servlet.http.HttpServletRequest;

/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose URL formating service
 */

public class URLFormatter {
	
	public static final String MEDIA_JSON = "json";
	public static final String MEDIA_PAGE = "page";
	
	public static String getMediaType(HttpServletRequest request){
		String mediaType= "";
		
		try	{
			String url = request.getRequestURL().toString();
			String[] urlParts =  url.split("\\.");
			mediaType = (urlParts.length> 0 ? urlParts[urlParts.length-1] : "");
			mediaType = mediaType.toLowerCase();
		}
		catch(Exception e){
			
		}
		return mediaType;
	}
}
