package com.hustar.edu.vote.sitemap;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;

@XmlAccessorType(value = XmlAccessType.NONE)
@XmlRootElement(name = "urlset")
public class XmlUrlSet {

    @XmlElements({@XmlElement(name = "url", type = XmlUrl.class)})
    private Collection<XmlUrl> xmlUrls = new ArrayList<XmlUrl>();

    public void addUrl(XmlUrl xmlUrl) {
        xmlUrls.add(xmlUrl);
    }

    public Collection<XmlUrl> getXmlUrls() {
        return xmlUrls;
    }
}
