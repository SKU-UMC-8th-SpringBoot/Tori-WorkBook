package umc.spring.service.StoreService;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.entity.Store;
import umc.spring.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

  private final StoreRepository storeRepository;
  //이 클래스는 StoreRepository에 의존한다!
  //즉, DB에서 데이터를 꺼내오는 건 Repository가 하고, 그걸 사용하는 게 이 서비스!

  @Override
  public Optional<Store> findStore(Long id) { //id로 특정 Store를 조회!!
    return storeRepository.findById(id); //결과를 Optional<Store>로 리턴! (값 잇을수도, 없을수도 있으니까!)
  }

  @Override
  public List<Store> findStoresByNameAndScore(String name, Float score) {
    //쿼리DSL을 통해 동적으로 쿼리를 구상한 메서드를 호출
    List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

    filteredStores.forEach(store -> System.out.println("Store: " + store));
    //filteredStores : List<Store> / ForEach() -> 리스트를 반복하며 요소를 하나씩 꺼냄!!
    //각 요소를 store라는 변수로 받아서, -> 후의 내용을 실행함.
    return filteredStores;
  }
}