package com.example.samrans.demodbsearchviewofflineonlinejson.models;

/**
 * Created by Samrans on 13-12-2017.
 */

class Dates {
    private String minimum;

    private String maximum;

    public String getMinimum ()
    {
        return minimum;
    }

    public void setMinimum (String minimum)
    {
        this.minimum = minimum;
    }

    public String getMaximum ()
    {
        return maximum;
    }

    public void setMaximum (String maximum)
    {
        this.maximum = maximum;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [minimum = "+minimum+", maximum = "+maximum+"]";
    }
}
