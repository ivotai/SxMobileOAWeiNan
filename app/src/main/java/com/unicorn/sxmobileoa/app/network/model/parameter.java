package com.unicorn.sxmobileoa.app.network.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

@SuppressWarnings("ALL")
public class parameter {

    public parameter() {
    }

    public parameter(String name, String text) {
        this.name = name;
        this.text = text;
    }

    @Attribute
    public String name;

    // require
    @Text(data = true)
    public String text;

}
