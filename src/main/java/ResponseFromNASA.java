import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.crypto.Data;

public class ResponseFromNASA {
    private String copyright;
    private String date;
    private String explanation;
    private String hdurl;
    @JsonProperty("media_type")
    private String mediaType;
    @JsonProperty("service_version")
    private String serviceVersion;
    private String title;
    private String url;

    ResponseFromNASA(){
    }

    public ResponseFromNASA(
            @JsonProperty("copyright") String copyright,
    @JsonProperty("date") String date,
    @JsonProperty("explanation") String explanation,
    @JsonProperty("hdurl") String hdurl,
    @JsonProperty("media_type") String mediaType,
    @JsonProperty("service_version") String serviceVersion,
    @JsonProperty("title") String title,
    @JsonProperty("url")  String url
    ){
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "copyright: " + copyright +
                "\n date: " + date +
                "\n explanation: " + explanation +
                "\n hdurl: " + hdurl +
                "\n mediaType: " + mediaType +
                "\n serviceVersion: " + serviceVersion +
                "\n title: " + title +
                "\n url: " + url;
    }
}
