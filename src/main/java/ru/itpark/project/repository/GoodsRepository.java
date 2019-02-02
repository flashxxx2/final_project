package ru.itpark.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.project.dto.GoodsDto;
import ru.itpark.project.entity.GoodsEntity;
import ru.itpark.project.entity.GoodsType;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {

    List<GoodsEntity> findAll();

    Optional<GoodsEntity> getById(int id);

    List<GoodsEntity> getByName(String name);

    List<GoodsEntity> findAllByNameContainsIgnoreCaseOrderByPriceDesc(String name);

      void removeById(int id);

      List<GoodsEntity> findAllByGoodstype(GoodsType goodsType);
}
