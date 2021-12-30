package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.SiteMap;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class SiteMapService {
    public static final String BASE_URL = "https://everyonespolitics.site/";
    public static final String BEGIN_DOC = "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";
    public static final String END_DOC = "</urlset>";
    public static final String CHANGEFREQ_ALWAYS = "always";
    public static final String CHANGEFREQ_HOURLY = "hourly";
    public static final String CHANGEFREQ_DAILY = "daily";
    public static final String CHANGEFREQ_WEEKLY = "weekly";
    public static final String CHANGEFREQ_MONTHLY = "monthly";
    public static final String CHANGEFREQ_YEARLY = "yearly";
    public static final String CHANGEFREQ_NEVER = "never";

    public String getSystemicSiteMap() throws UnsupportedEncodingException {
        Date now = new Date();
        StringBuffer sb = new StringBuffer();
        sb.append(BEGIN_DOC);
        sb.append(new SiteMap(BASE_URL, now, CHANGEFREQ_NEVER, "1.0"));
        return sb.toString();
    }
}
