package ru.itpark.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private Integer goodsId;
    private Integer userId;
    private Long time;
 }
