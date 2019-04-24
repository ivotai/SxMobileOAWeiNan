package com.unicorn.sxmobileoa.app.network.model;

import org.simpleframework.xml.ElementList;

import java.util.List;

public class parameters {

    @ElementList(entry = "parameter",inline = true)
    public List<parameter> parameterList;

}
