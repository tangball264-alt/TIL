# Stream API

## 개념

Stream :  [배열](Array.md)이나 [컬렉션(List 등)](컬렉션.md)의 데이터를 파이프라인처럼 연속된 작업으로 처리하는 방식. 

컬렉션이나 배열 등의 데이터 소스를 함수형 스타일로 처리할 수 있게 해주는 API

java.util.stream 패키지에 있다.

## 특징

1. 데이터를 변경하지 않음
Stream은 원본 데이터 소스를 변경하지 않고, 데이터를 읽어서 처리한 결과만 반환합니다.

2. 일회용(One-time use)
Stream은 한 번 사용(최종 연산 수행)하면 재사용할 수 없습니다. 다시 쓰려면 스트림을 새로 만들어야 합니다.

3. 지연 연산(Lazy evaluation)
중간 연산들은 최종 연산이 호출되기 전까지 실제로 실행되지 않습니다.

## 구조

데이터_소스.stream() > 중간연산(0개이상) > 최종연산(1개)

1. 스트림 생성
```java
List<Integer> result = numbers.stream();
```
- .stream(), Arrays.stream(), Stream.of() 등을 이용해 생성.
2. 중간 연산
.filter().map().sorted() ... 처럼 계속 이어 붙일 수 있음(체이닝)
3. 최종 연산
실제 결과값 리턴
- .collect()등. 이 시점에 실행됨. 
## 