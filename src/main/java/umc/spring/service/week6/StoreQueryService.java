package umc.spring.service.week6;

import umc.spring.domain.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, float score);
}
