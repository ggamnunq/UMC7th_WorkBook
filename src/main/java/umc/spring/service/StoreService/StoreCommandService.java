package umc.spring.service.StoreService;


import org.springframework.web.multipart.MultipartFile;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.web.dto.StoreRequestDTO;


public interface StoreCommandService {

    public Store add(StoreRequestDTO.AddDto request);


}
