package com.dhu.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by demerzel on 2018/3/30.
 */
@Entity
@Table(name = "seat", schema = "MCMS", catalog = "")
public class SeatEntity {
    private int id;
    private int row;
    private int col;
    private int hallId;

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
    @Column(name = "row", nullable = false)
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Basic
    @Column(name = "col", nullable = false)
    public int getCol() {
        return col;
    }

    public void setCol(int column) {
        this.col = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatEntity that = (SeatEntity) o;
        return id == that.id &&
                row == that.row &&
                col == that.col;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, row, col);
    }

    @Basic
    @Column(name = "hall_id", nullable = false)
    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }
}
