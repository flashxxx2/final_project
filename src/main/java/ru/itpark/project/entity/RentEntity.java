package ru.itpark.project.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rent_information")
public class RentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="end_time")
    private LocalDateTime endTime;

    @Column(name = "time")
    private Long time;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private GoodsEntity goods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }
}
