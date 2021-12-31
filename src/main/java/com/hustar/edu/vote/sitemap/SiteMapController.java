package com.hustar.edu.vote.sitemap;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SiteMapController {

    @RequestMapping(value="/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody
    public XmlUrlSet main() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        create(xmlUrlSet, "", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/vote/promiseList", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/vote/behindList", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/vote/boardList", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/vote/boardDetail?idx=2", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/vote/boardDetail?idx=2", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/vote/voting", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/vote/boardList?page=1&filter=all&keyword=", XmlUrl.Priority.ROW);

        return xmlUrlSet;
    }

    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
        xmlUrlSet.addUrl(new XmlUrl("https://www.everyonespolitics.site"+link, priority));
    }
}