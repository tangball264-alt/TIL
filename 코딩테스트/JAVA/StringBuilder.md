# StringBuilder

자바에서 문자열을 더하거나 변경할 때 메모리를 아끼고 속도를 획기적으로 높이기 위해 사용하는 클래스

## String과 다른 점

String : 불변. 따라서 "A"+"B"를 하면 A가 변경되는 게 아니라 A(쓰레기), B(쓰레기), AB(신규)의 문자열 객체가 됨. 따라서 반복적으로 더할수록 쓰레기 객체 증가.

StringBuilder : 가변. 내부에 가변 내장 버퍼를 두어 문자 추가/삭제 시 새 객체를 만들지 않고 기존 메모리 공간을 늘리거나 줄여 수정. 속도가 빠르고 쓰레기 객체가 없음.

## 사용법

```java
StringBuilder sb = new StringBuilder();
sb.append("Java"); //문자열을 맨 뒤에 추가.
sb.insert(4, "Code"); //지정 인덱스 위치에 문자열 삽입
sb.delete(4,9); //두 인덱스 범위(시작 인덱스 포함, 끝 인덱스 제외)문자열 삭제.
sb.reverse(); //문자열 순서 뒤집기.
sb.toString(); //StringBuilder를 String으로 변환.
```

## 특징

반복문에서의 문자열 조작에 매우 유리(효율성)

코딩테스트 문자열 단골

Stream.builder()랑은 별개

하위 메서드들 체이닝 가능.

## 라이브러리
기본 패키지인 java.lang에 포함. 따라서 import 불필요.