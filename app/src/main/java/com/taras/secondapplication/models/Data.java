package com.taras.secondapplication.models;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Data extends RealmObject
{
    private String is_silhouette;

    private String url;

    public String getIs_silhouette ()
    {
        return is_silhouette;
    }

    public void setIs_silhouette (String is_silhouette)
    {
        this.is_silhouette = is_silhouette;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [is_silhouette = "+is_silhouette+", url = "+url+"]";
    }
}
