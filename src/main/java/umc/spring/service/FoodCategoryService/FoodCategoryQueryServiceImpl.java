package umc.spring.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.FoodCategoryRepository.FoodCategoryRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodCategoryQueryServiceImpl implements FoodCategoryQueryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean existsById(List<Long> values) {
        return values.stream().allMatch(value -> foodCategoryRepository.existsById(value));
    }
}
