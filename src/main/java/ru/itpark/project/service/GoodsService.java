package ru.itpark.project.service;

import org.springframework.stereotype.Service;
import ru.itpark.project.dto.GoodsDto;
import ru.itpark.project.entity.GoodsEntity;
import ru.itpark.project.exception.ProductNotFoundException;
import ru.itpark.project.repository.GoodsRepository;

@Service
public class GoodsService {
    private final GoodsRepository repository;

    public GoodsService(GoodsRepository repository) {
        this.repository = repository;
    }
    public GoodsEntity getById(int id){
        return repository.getById(id).orElseThrow(ProductNotFoundException::new);
    }
    public void save (GoodsEntity item){
        repository.save(item);
    }
    public void removeById(int id){
        repository.removeById(id);
    }
}
