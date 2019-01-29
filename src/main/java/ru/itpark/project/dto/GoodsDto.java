package ru.itpark.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto {
    private int id;
    private String name;
    private String describe;
    private int price;
    private String imageUrl;

}
