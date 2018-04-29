package com.vithu.uscms.others;


/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose Return result definition - to return complex results in methods
 */
public class GenericResult
{
    private boolean status;
    private String message;
    private String description;
    private Object result;
    private String requestedFormat;
    private String resultString;

    // *************************************************************
    // ** CONSTRUCTORS
    // *************************************************************
    public GenericResult(Object result) {
  		super();
  		this.result = result;
  	}
    public GenericResult( boolean status, String message, String description )
    {
        super();
        this.status = status;
        this.message = message;
        this.description = description;
    }

    public GenericResult( boolean status, String message, String description, Object result )
    {
        super();
        this.status = status;
        this.message = message;
        this.description = description;
        this.result = result;
    }

    public GenericResult(boolean status, String message, String description, Object result, String requestedFormat) {
		super();
		this.status = status;
		this.message = message;
		this.description = description;
		this.result = result;
		this.requestedFormat = requestedFormat;
	}

	public GenericResult()
    {
        super();
    }

    public GenericResult(boolean status, String message, String description, Object result, String resultString, String requestedFormat	) {
		super();
		this.status = status;
		this.message = message;
		this.description = description;
		this.result = result;
		this.requestedFormat = requestedFormat;
		this.resultString = resultString;
	}

	// *************************************************************
    // ** GETTERS AND SETTERS
    // *************************************************************
	 public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

    public boolean isStatus()
    {
        return status;
    }

	public void setStatus( boolean status )
    {
        this.status = status;
    }

    
    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public Object getResult()
    {
        return result;
    }

    public void setResult( Object result )
    {
        this.result = result;
    }

	public String getRequestedFormat() {
		return requestedFormat;
	}

	public void setRequestedFormat(String requestedFormat) {
		this.requestedFormat = requestedFormat;
	}
}
