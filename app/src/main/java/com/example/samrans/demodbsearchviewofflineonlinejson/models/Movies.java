package com.example.samrans.demodbsearchviewofflineonlinejson.models;

/**
 * Created by Samrans on 13-12-2017.
 */

public class Movies {
    private Results[] results;

    private Dates dates;

    private String page;

    private String total_pages;

    private String total_results;

    public Results[] getResults ()
    {
        return results;
    }

    public void setResults (Results[] results)
    {
        this.results = results;
    }

    public Dates getDates ()
    {
        return dates;
    }

    public void setDates (Dates dates)
    {
        this.dates = dates;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (String total_pages)
    {
        this.total_pages = total_pages;
    }

    public String getTotal_results ()
    {
        return total_results;
    }

    public void setTotal_results (String total_results)
    {
        this.total_results = total_results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", dates = "+dates+", page = "+page+", total_pages = "+total_pages+", total_results = "+total_results+"]";
    }
}
