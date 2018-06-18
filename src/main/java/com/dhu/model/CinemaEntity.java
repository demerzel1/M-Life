package com.dhu.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by demerzel on 2018/3/30.
 */
@Entity
@Table(name = "cinema", schema = "MCMS", catalog = "")
public class CinemaEntity {
    private int id;
    private String name;
    private String address;
    private int cityId;
    private String lat;
    private String lng;
    private String latNow;
    private String lngNow;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaEntity that = (CinemaEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, address);
    }

    @Basic
    @Column(name = "city_id", nullable = false)
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "lat", nullable = true, length = 50)
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "lng", nullable = true, length = 50)
    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Basic
    @Column(name = "lat_now", nullable = true, length = 50)
    public String getLatNow() {
        return latNow;
    }

    public void setLatNow(String latNow) {
        this.latNow = latNow;
    }

    @Basic
    @Column(name = "lng_now", nullable = true, length = 50)
    public String getLngNow() {
        return lngNow;
    }

    public void setLngNow(String lngNow) {
        this.lngNow = lngNow;
    }
}
