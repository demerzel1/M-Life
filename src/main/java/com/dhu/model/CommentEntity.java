package com.dhu.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by demerzel on 2018/4/12.
 */
@Entity
@Table(name = "comment", schema = "MCMS", catalog = "")
public class CommentEntity {
    private int id;
    private int userId;
    private int movieId;
    private String content;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private MovieEntity movieEntity;
    private Integer grade;

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
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "movie_id", nullable = false)
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return id == that.id &&
                userId == that.userId &&
                movieId == that.movieId &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, movieId, content);
    }

    @Basic
    @Column(name = "grade", nullable = true)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
