# switch-case문에서의 변수 중복 선언 오류

## 문제

문제 설명
머쓱이네 옷가게는 10만 원 이상 사면 5%, 30만 원 이상 사면 10%, 50만 원 이상 사면 20%를 할인해줍니다.
구매한 옷의 가격 price가 주어질 때, 지불해야 할 금액을 return 하도록 solution 함수를 완성해보세요.

## 오류 답안

```java
class Solution {
    public int solution(int price) {
        switch (price/100000) {    
            case 1:        
                int answer = price*95/100;       
                break;    
            case 3:        
                int answer = price*9/10;
                break;    
            case 5:        
                int answer = price*8/10;
                break;    
            default:        
                int answer = price;
        }
        return answer;
    }
}
```

## 오류 사안

int answer의 중복 선언 문제 발생.

-> 해당 문제는 **컴파일 단계**에서 발생.

break;문을 통해 각 case를 분리하였으니 정상 작동할 거라고 판단했으나, break문은 런타임 중 실행 흐름을 제어하지 컴파일 단계에서 진행되는 변수 중복 검사에는 영향을 미치지 못함.

case는 스코프를 나누는 경계가 아니라 단순 위치 라벨.(스코프 분리 기준은 {중괄호} 분리.)

## 해결 방법

1. 변수 선언을 switch 문 밖으로 뺀다.
2. 변수 대신 return문으로 바로 반환한다.(이 경우 break도 불필요)
3. if-else문으로 바꾼다.