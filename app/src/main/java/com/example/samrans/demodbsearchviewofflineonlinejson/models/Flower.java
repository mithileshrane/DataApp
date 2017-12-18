package com.example.samrans.demodbsearchviewofflineonlinejson.models;

import java.io.Serializable;

/**
 * Created by Samrans on 12-12-2017.
 */

public class Flower implements Serializable {
    private String category;

    private String price;

    private String instructions;

    private String name;

    private String photo;

    private String productId;

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getInstructions ()
    {
        return instructions;
    }

    public void setInstructions (String instructions)
    {
        this.instructions = instructions;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPhoto ()
    {
        return photo;
    }

    public void setPhoto (String photo)
    {
        this.photo = photo;
    }

    public String getProductId ()
    {
        return productId;
    }

    public void setProductId (String productId)
    {
        this.productId = productId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [category = "+category+", price = "+price+", instructions = "+instructions+", name = "+name+", photo = "+photo+", productId = "+productId+"]";
    }
}
