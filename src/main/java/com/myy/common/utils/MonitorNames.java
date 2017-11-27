package com.myy.common.utils;

/**
 * @author caozhaorui
 */
public interface MonitorNames {

    interface Crawler {
        String Prefix = "Crawler";
        String Total = Prefix + "_Total";
        String ResultStatus = Prefix + "_ResultStatus";

        String ProxyWarning = Prefix + "_Proxy_Warning";
        String Proxy = Prefix + "_Proxy";
    }

    interface NewsCrawler {
        String Prefix = "Crawler_News";
        String ContentCheck = Prefix + "Content_Check";
    }

    interface Email {
        String Prefix = "Crawler_Email";
        String SendEmail = Prefix + "_SendEmail";
    }

    interface Video {
        String Prefix = "Video";
        String Sync = Prefix + "_Sync";
        String Info = Prefix + "_Info";
        String UploadImage = Prefix + "_UploadImage";

    }

    interface AWS {
        String Prefix = "AWS";
        String Upload = Prefix + "_Upload";
    }

    interface Youtube {
        String Prefix = "Youtube_";
        String Subscribe = Prefix + "Subscribe";
    }

}
