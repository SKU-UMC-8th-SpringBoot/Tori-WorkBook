package umc.spring.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.MemberMission;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer reward;

  private LocalDate deadline;

  private String missionSpec;

  @ManyToOne(fetch = FetchType.LAZY) //자연 로딩을 설정함.
  @JoinColumn(name = "store_id")  //실제 데이터베이스에서 해당 칼럼(외래키)의 이름을 설정하는 것!!
  private Store store;

  @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
  private List<MemberMission> memberMissionList = new ArrayList<>();
}
