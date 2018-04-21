package com.vithu.uscms.entities;

/**
 * @author M.Vithusanth
 * @CreatedOn 21th December 2017
 * @Purpose User Entity Class
 */
public class Customer
{
    private Integer id;
    private User user;
    
    //*************************************************************
    //** CONSTRUCTORS
    //*************************************************************
    

    //*************************************************************
    //** GETTERS AND SETTERS
    //*************************************************************
    public Integer getId()
    {
        return id;
    }
    public void setId( Integer id )
    {
        this.id = id;
    }
    public User getUser()
    {
        return user;
    }
    public void setUser( User user )
    {
        this.user = user;
    }
}
