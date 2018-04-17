package com.dhu.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by demerzel on 2018/3/30.
 */
@Entity
@Table(name = "movie", schema = "MCMS", catalog = "")
public class MovieEntity {
    private int id;
    private String name;
    private String englishname;
    private String director;
    private String cast;
    private String description;
    private String poster;
    private Date beginTime;
    private Date endTime;
    private String movieType;
    private Integer duration;
    private String country;
    private Double score;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Basic
    @Column(name = "englishname", nullable = true, length = 50)
    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    @Basic
    @Column(name = "director", nullable = true, length = 50)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "cast", nullable = true, length = 200)
    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "poster", nullable = true, length = 50)
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Basic
    @Column(name = "begin_time", nullable = true)
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "movie_type", nullable = true, length = 200)
    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    @Basic
    @Column(name = "duration", nullable = true)
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "country", nullable = true, length = 50)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "score", nullable = true, precision = 0)
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
