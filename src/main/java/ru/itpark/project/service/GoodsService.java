package ru.itpark.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itpark.project.dto.RentDto;
import ru.itpark.project.entity.GoodsEntity;
import ru.itpark.project.entity.GoodsType;
import ru.itpark.project.entity.RentEntity;
import ru.itpark.project.exception.ProductNotFoundException;
import ru.itpark.project.repository.GoodsRepository;
import ru.itpark.project.repository.RentRepository;
import ru.itpark.project.util.RentUtil;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final RentRepository rentRepository;
    private final Path uploadPath;

    @Autowired
    public GoodsService(GoodsRepository goodsRepository, RentRepository rentRepository,
                        @Value("${spring.resources.static-locations}") String uploadPath) {
        this.goodsRepository = goodsRepository;
        this.rentRepository = rentRepository;
        this.uploadPath = Path.of(URI.create(uploadPath)).resolve("media");
        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GoodsEntity> getAll() {
        return goodsRepository.findAll();
    }

    public GoodsEntity getById(int id) {
        Optional<GoodsEntity> optionalGoodsEntity = goodsRepository.getById(id);
        return optionalGoodsEntity.orElseThrow(ProductNotFoundException::new);

    }

    public void save(GoodsEntity item) {
        goodsRepository.save(item);
    }

    public void removeById(int id) {
        goodsRepository.removeById(id);
    }

    public List<GoodsEntity> findByName(String name) {
        return goodsRepository.findAllByNameContainsIgnoreCaseOrderByPriceDesc(name);
    }

    public List<GoodsEntity> findByType(GoodsType type) {
        return goodsRepository.findAllByGoodstype(type);

    }

    public RentEntity save(RentDto rentDto) {
        RentEntity rentEntity = new RentEntity();
        Long time = rentDto.getTime();
        LocalDateTime endTime = RentUtil.calculateRentEndTime(time);
        rentEntity.setEndTime(endTime);
        Optional<GoodsEntity> optionalGoodsEntity = goodsRepository.getById(rentDto.getGoodsId());
        GoodsEntity goodsEntity = optionalGoodsEntity.orElseThrow(ProductNotFoundException::new);
        rentEntity.setGoods(goodsEntity);
        rentEntity.setTime(time);
        if (time > 0) {
            RentEntity save = rentRepository.save(rentEntity);
            return save;
        } else throw new ProductNotFoundException();


    }

    public RentEntity getCurrentRent() {
        List<RentEntity> rentEntityList = rentRepository.getByEndTimeAfter(LocalDateTime.now());
        if (rentEntityList.isEmpty())
            return null;
        return rentEntityList.get(0);
    }
}



