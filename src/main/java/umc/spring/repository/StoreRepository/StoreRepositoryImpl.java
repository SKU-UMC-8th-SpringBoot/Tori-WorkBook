package umc.spring.repository.StoreRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.entity.Store;
import umc.spring.domain.entity.QStore;

@Repository //이 클래스를 DB 작업용 클래스로 인식하게 해줌.
@RequiredArgsConstructor //final로 선언된 필드를 자동으로 생성자 주입해준다.
public class StoreRepositoryImpl implements StoreRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory; //QueryDSL쿼리를 만들기 위한 핵심 개체!! (의존성 주입됨)
  private final QStore store = QStore.store; //Store엔티티를 쿼리DSL용으로 만든 Q타입 클래스의 인스턴스!

  @Override
  public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
    BooleanBuilder predicate = new BooleanBuilder();

    if (name != null) {
      predicate.and(store.name.eq(name));
    }

    if (score != null) {
      predicate.and(store.score.goe(4.0f));
    }

    return jpaQueryFactory
        .selectFrom(store)
        .where(predicate)
        .fetch();
  }
}