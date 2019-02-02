package ru.itpark.project.service;

import org.springframework.stereotype.Service;
import ru.itpark.project.dto.GoodsDto;
import ru.itpark.project.entity.GoodsEntity;
import ru.itpark.project.entity.GoodsType;
import ru.itpark.project.exception.ProductNotFoundException;
import ru.itpark.project.repository.GoodsRepository;

import java.util.List;

@Service
public class GoodsService {
    private final GoodsRepository repository;

    public GoodsService(GoodsRepository repository) {
        this.repository = repository;
    }

    public List<GoodsEntity> getAll() {
        return repository.findAll();
    }

    public GoodsEntity getById(int id) {
        return repository.getById(id).orElseThrow(ProductNotFoundException::new);
    }

    public void save(GoodsEntity item) {
        repository.save(item);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public List<GoodsEntity> findByName(String name) {
        return repository.findAllByNameContainsIgnoreCaseOrderByPriceDesc(name);
    }
    public List<GoodsEntity> findByType(GoodsType type){
        return repository.findAllByGoodstype(type);

    }
}

