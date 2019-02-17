package ru.itpark.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.project.entity.GoodsEntity;
import ru.itpark.project.entity.GoodsType;
import ru.itpark.project.entity.RentEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<RentEntity, Integer> {


    List<RentEntity> getByEndTimeAfter(LocalDateTime time);
}
