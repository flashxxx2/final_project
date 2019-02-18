package ru.itpark.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "describe", length = 600000)
    private String describe;

    @Column(name = "price")
    private Long price;

    @Column(name = "goods_type")
    @Enumerated(EnumType.STRING)
    private GoodsType goodstype;

    private String path;

}
