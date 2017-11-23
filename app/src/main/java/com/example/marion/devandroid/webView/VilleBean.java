package com.example.marion.devandroid.webView;

public class VilleBean
{

    private String cp;

    private String ville;

    public String getCp ()
    {
        return cp;
    }

    public void setCp (String cp)
    {
        this.cp = cp;
    }

    public String getVille ()
    {
        return ville;
    }

    public void setVille (String ville)
    {
        this.ville = ville;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cp = "+cp+", ville = "+ville+"]";
    }

}
