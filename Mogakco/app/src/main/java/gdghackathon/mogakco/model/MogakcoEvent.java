package gdghackathon.mogakco.model;

import java.io.Serializable;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */
// model for mock data
public class MogakcoEvent implements Serializable{
    String title;
    String imageUrl;
    String description;
    float latitude;
    float longitude;


    public MogakcoEvent(String title) {
        this.title = title;
    }

    public MogakcoEvent(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public MogakcoEvent(String title, String imageUrl, float latitude, float longitude) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
