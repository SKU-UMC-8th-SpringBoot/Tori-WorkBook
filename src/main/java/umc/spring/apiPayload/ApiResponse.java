package umc.spring.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.apiPayload.code.BaseCode;
import umc.spring.apiPayload.code.status.SuccessStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {  //응답 데이터의 타입을 유연하게 받을 수 있게 제네릭 사용!!

  @JsonProperty("isSuccess")  //-> JSON 키 이름을 isSuccess로 명시적으로 지정
  private final Boolean isSuccess; //응답이 성공인지 실패인지 나타냄!!
  private final String code;  //상태코드 2000같은 커스텀 코드 사용 가능
  private final String message; //사용자에게 전달할 메세지
  @JsonInclude(JsonInclude.Include.NON_NULL) //JsonInclude가 null이면 아예 응답에 포함되지 않도록 함 (불필요한 필드 제거 목적)
  private T result; //실제 응답 데이터


    //성공한 경우 응답 생성

    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessStatus._OK.getCode() , SuccessStatus._OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result){
          return new ApiResponse<>(true, code.getReasonHttpStatus().getCode() , code.getReasonHttpStatus().getMessage(), result);
    }

  //이 클래스 사용 예시!!
  //@GetMapping("/user/{id}")
  //public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable Long id) {
  //    UserDto user = userService.findUser(id);
  //    return ResponseEntity.ok(ApiResponse.onSuccess(user));
  //}

  // 실패한 경우 응답 생성
  public static <T> ApiResponse<T> onFailure(String code, String message, T data){
    return new ApiResponse<>(false, code, message, data);
  }
}