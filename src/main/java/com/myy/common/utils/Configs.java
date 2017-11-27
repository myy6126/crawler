package com.myy.common.utils;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author caozhaorui
 */
public class Configs {

    private static final Configuration configProperties;

    static {
        PropertiesBuilderParameters parameters = new Parameters().properties().setFileName("crawl.properties");
        try {
            configProperties = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                    .configure(parameters).getConfiguration();
        } catch (ConfigurationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static String getProxyFilePath() {
        return configProperties.getString("proxy.file.path");
    }

    public static int getTimeOut() {
        return configProperties.getInt("conn.timeout");
    }

    public static String getTestProxyUrl() {
        return configProperties.getString("test.proxy.url");
    }

    public static String getBusNotifyUrl() {
        return configProperties.getString("bus.notify.url");
    }

    public static String getPromotionUpdateMailTo() {
        return configProperties.getString("mail.promotionUpdateMailTo");
    }

    public static String getPromotionAlertMailTo() {
        return configProperties.getString("mail.promotionAlertMailTo");
    }

    public static String getPromotionInitCrawlMailTo() {
        return configProperties.getString("mail.promotionInitCrawlMailTo");
    }

    public static String getImageUploadHost() {
        return configProperties.getString("image.upload.host");
    }

    public static String getReturnProductIds() {
        return configProperties.getString("productids.return.url");
    }

    public static String getReturnProductDetail() {
        return configProperties.getString("product.detail.return.url");
    }

    public static String getHotCategoriesFetchItems() {
        return configProperties.getString("hotcategories.fetchitems.url");
    }

    public static String getStoreFileBase() {
        return configProperties.getString("store.file.base");
    }

    public static String getNewsFolder() {
        return configProperties.getString("news.video.folder");
    }

    public static String getVideoBucket() {
        return configProperties.getString("aws.video.bucket");
    }

    public static String getVideoHost() {
        return configProperties.getString("aws.video.host");
    }


    public static String getInvalidLinkRemind() {
        return configProperties.getString("video.remind.invalidLink");
    }

    public static String getMaxSizeRemind() {
        return configProperties.getString("video.remind.maxSize");
    }
    public static String getTempFilePath() {
        return configProperties.getString("temp.file.path");
    }

    public static int getVideoUploadMaxSize() {
        return configProperties.getInt("video.upload.maxSize");
    }

    public static String getCrawlerHost() {
        return configProperties.getString("crawler.host");
    }

    public static String getYoutubeFailedEmailAddress() {
        return configProperties.getString("mail.youtube.subscribe.failed.address");
    }

    public static String getValue(String key) {
        if (StringUtils.isBlank(key)) {
            return StringUtils.EMPTY;
        }
        return configProperties.getString(key);
    }
}
