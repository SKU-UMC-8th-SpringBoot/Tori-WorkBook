package umc.spring.service.StoreService;

import java.util.List;
import java.util.Optional;
import umc.spring.domain.entity.Store;

public interface StoreQueryService {
  Optional<Store> findStore(Long id); //Optional<Store>: 결과가 있을 수도 있고 없을 수도 있어서 null을 안전하게 처리하기 위해 사용
  List<Store> findStoresByNameAndScore(String name, Float score); //이름, 점수 조건으로 Store목록을 조회
  //그리고, 조건에 맞는 가게들을 List로 반환함!! 아까 봤던 쿼니DSL메서드를 사용해서!

}
