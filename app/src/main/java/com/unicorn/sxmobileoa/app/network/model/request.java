package com.unicorn.sxmobileoa.app.network.model;

import com.blankj.utilcode.util.AppUtils;
import com.unicorn.sxmobileoa.app.Global;
import com.unicorn.sxmobileoa.app.Key;
import com.unicorn.sxmobileoa.app.mess.RandomGeneter;

import org.joda.time.DateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@SuppressWarnings("ALL")
@Root
public class request {

    protected request(String busiCode) {
        this.busiCode = busiCode;
        parameters = new parameters();
        parameters.parameterList = new ArrayList<>();
    }

    protected final void addParameter(String key, String value) {
        parameters.parameterList.add(new parameter(key, value));
    }

    @Element
    public String requestFlow = new DateTime().toString(Key.DATE_FORMAT) + RandomGeneter.generateString(6);

    @Element
    public String version = AppUtils.getAppVersionName();

    @Element
    public String UUID = "";

    @Element
    public String busiCode;

    @Element
    public String loginName = Global.INSTANCE.getLoginInfo() == null ? "" : Global.INSTANCE.getLoginInfo().getLoginName();

    @Element
    public String loginBusiType = Global.INSTANCE.getLoginInfo() == null ? "" : Global.INSTANCE.getLoginInfo().getLoginBusiType();

    @Element
    public String ticket = Global.INSTANCE.getTicket() == null ? "" : Global.INSTANCE.getTicket();

    @Element
    public String randCode = RandomGeneter.generateString(12);

    @Element
    public String randCodeSec = "";

    @Element
    public String time = new DateTime().toString(Key.DATE_FORMAT);

    @Element
    public String phoneType = "android";

    @Element
    public parameters parameters;

}
