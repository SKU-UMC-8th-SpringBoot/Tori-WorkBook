package umc.spring.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QueryDSLConfig {

  private final EntityManager entityManager;

  @Bean //QueryDSL을 사용하려면, JPAQueryFactory 를 통하여 쿼리를 작성해야 함.
  //따라서, 얘를 Bean으로 등록하는 과정이 필요!!
  public JPAQueryFactory jpaQueryFactory(){
    return new JPAQueryFactory(entityManager);
  }
}
