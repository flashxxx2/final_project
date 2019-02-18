package ru.itpark.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.project.entity.RentEntity;

import java.time.LocalDateTime;
import java.util.List;
public interface RentRepository extends JpaRepository<RentEntity, Integer> {


    List<RentEntity> getByEndTimeAfterAndGoodsId(LocalDateTime time, Integer goodsId);
}
