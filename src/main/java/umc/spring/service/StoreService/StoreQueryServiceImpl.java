package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.domain.entity.Store;
import umc.spring.repository.StoreRepository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, float score) {
        List<Store> filteredScores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
        filteredScores.forEach(store -> System.out.println("Store : " + store));
        return filteredScores;
    }

    @Override
    public boolean existsById(Long id) {
        return storeRepository.existsById(id);
    }
}
