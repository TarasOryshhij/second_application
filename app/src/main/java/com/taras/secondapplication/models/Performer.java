package com.taras.secondapplication.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pinta on 18.05.2016.
 */
public class Performer extends RealmObject {

    @PrimaryKey
    private long id;

    private String person;

    private String organization;

    private String deadline;

    public long getId ()
    {
        return id;
    }

    public void setId (long id)
    {
        this.id = id;
    }

    public String getPerson ()
    {
        return person;
    }

    public void setPerson (String person)
    {
        this.person = person;
    }

    public String getOrganization ()
    {
        return organization;
    }

    public void setOrganization (String organization)
    {
        this.organization = organization;
    }

    public String getDeadline ()
    {
        return deadline;
    }

    public void setDeadline (String deadline)
    {
        this.deadline = deadline;
    }

}
