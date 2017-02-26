package com.jaxio.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Token implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4742698642702870312L;

	private String series;

    private String value;

    private Date date;

    private String ipAddress;

    private String userAgent;

    private String userLogin;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

}
