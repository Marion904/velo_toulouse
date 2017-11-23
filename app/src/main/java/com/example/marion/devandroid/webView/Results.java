package com.example.marion.devandroid.webView;


import java.util.ArrayList;

public class Results
{
    private int nbr;

   //private Results[] results;

    private ArrayList<VilleBean> results ;
    private ErrorBean error;



    public ArrayList<VilleBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<VilleBean> results) {
        this.results = results;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }



    public int getNbr ()
    {
        return nbr;
    }

    public void setNbr (int nbr)
    {
        this.nbr = nbr;
    }

    /**public Results[] getResults ()
    {
        return results;
    }

    public void setResults (Results[] results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {

        String lignederesult = "";
        for(VilleBean v : this.getVilleBeans()){
            lignederesult += v.getVille() ;
        }
        return "ClassPojo [nbr = "+nbr+", results = "+ lignederesult +"]";
    }
    **/
}
