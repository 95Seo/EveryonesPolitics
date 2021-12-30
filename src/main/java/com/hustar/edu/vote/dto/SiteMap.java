package com.hustar.edu.vote.dto;

import com.hustar.edu.vote.service.SiteMapService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SiteMap {
    private static final SimpleDateFormat SITE_MAP_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public SiteMap(String loc) {
        this.loc = loc;
        this.lastmod = new Date();
        this.changefreq = SiteMapService.CHANGEFREQ_NEVER;
        this.priority = "1.0";
    }
    public SiteMap(String loc, Date lastmod, String changefreq, String priority) {
        this.loc = loc;
        this.lastmod = lastmod;
        this.changefreq = changefreq;
        this.priority = priority;
    }
    /**
     * url
     */
    private String loc;
    /**
     * yyyy-MM-dd
     */
    private Date lastmod;
    /**
     * always hourly daily weekly monthly yearly never
     */
    private String changefreq;
    /**
     * 1.0 0.9 0.8
     */
    private String priority;
    public String getLoc() {
        return loc;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public Date getLastmod() {
        return lastmod;
    }
    public void setLastmod(Date lastmod) {
        this.lastmod = lastmod;
    }
    public String getChangefreq() {
        return changefreq;
    }
    public void setChangefreq(String changefreq) {
        this.changefreq = changefreq;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<url>");
        sb.append("<loc>" + loc + "</loc>");
        sb.append("<lastmod>" + SITE_MAP_DATE_FORMAT.format(lastmod) + "</lastmod>");
        sb.append("<changefreq>" + changefreq + "</changefreq>");
        sb.append("<priority>" + priority + "</priority>");
        sb.append("</url>");
        return sb.toString();
    }
}