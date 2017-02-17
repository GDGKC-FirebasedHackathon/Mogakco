package gdghackathon.mogakco.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */

@IgnoreExtraProperties
public class MogakcoEvent implements Serializable{
    String title;
    String image;
    String description;
    String address;
    float latitude;
    float longitude;


    public MogakcoEvent(String title) {
        this.title = title;
    }

    public MogakcoEvent(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public MogakcoEvent(String title, String image, float latitude, float longitude) {
        this.title = title;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
