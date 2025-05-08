package umc.spring.repository.StoreRepository;

import java.util.List;
import umc.spring.domain.entity.Store;

public interface StoreRepositoryCustom {

  List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}
//StoreRepositoryCustom ← 사용자 정의 인터페이스
//StoreRepositoryImpl ← 그 인터페이스를 구현하는 클래스