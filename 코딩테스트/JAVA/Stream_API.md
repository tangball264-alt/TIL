# Stream API

## 개념

Stream :  [배열](Array.md)이나 [컬렉션(List 등)](컬렉션.md)의 데이터를 파이프라인처럼 연속된 작업으로 처리하는 방식. 

컬렉션이나 배열 등의 데이터 소스를 람다를 활용해 함수형 스타일로 처리할 수 있게 해주는 API

java.util.stream 패키지에 있다.

## 특징

1. 데이터를 변경하지 않음
Stream은 원본 데이터 소스를 변경하지 않고, 읽기만 한다.

2. 일회용(One-time use)
Stream은 한 번 사용(=최종 연산 수행)하면 닫혀서 재사용 불가.

3. 지연 연산(Lazy evaluation)
중간 연산들은 최종 연산이 호출되기 전까지 실제로 실행되지 않는다.

4. 작업을 내부 반복으로 처리
ex) forEach() 는 매개변수에 대입된 람다식을 데이터 소스의 전 요소에 적용함.

5. 병렬 처리가 쉽다
멀티스레드 사용.

6. 기본형 스트림 제공
Stream<Integer> 대신 기본형스트림 IntStream이 제공됨. 오토박싱과 언박싱 등 생략하고 .sum(), .average()등의 유용한 메소드 추가로 제공.
       

## 구조

데이터_소스.stream() > 중간연산(0개이상) > 최종연산(1개)

---

### 스트림 생성

1. 배열 스트림 : Arrays.stream()
```java
String[] arr = new String[]{"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);
```
2. 컬렉션 스트림 : .stream()
```java
List<String> list = Arrays.asList("a","b","c");
Stream<String> stream = list.stream();
```
3. Stream.builder()
```java
Stream<String> builderStream = Stream.<String>builder()
    .add("a").add("b").add("c")
    .build(); 
```
4. 람다식 Stream.generate(), iterate()
```java
Stream<String> generatedStream = Stream.generate(()->"a").limit(3);
// 생성할 때 스트림의 크기가 정해져있지 않기(무한하기)때문에 최대 크기를 제한해줘야 한다.

Stream<Integer> iteratedStream = Stream.iterate(0, n->n+2).limit(5); //0,2,4,6,8
```
5. 기본 타입형 스트림
```java
IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4]
```
6. 병렬 스트림
```java
Stream<String> parallelStream = list.parallelStream();
```

---

### 중간 연산

**여러 중간 연산을 이어붙일 수 있음**

1. Filtering
```java
List<String> list = Arrays.asList("a","b","c");
Stream<String> stream = list.stream()
	.filter(list -> list.contains("a"));
    // 'a'가 들어간 요소만 선택  [a]
```
- 스트림 내 요소들을 필터링하는 if문의 역할.
- 조건문이 되는 람다를 인자로 받는다.
- .filter(람다식) => 정식 표기로는 filter(Predicate<T>)
2. Mapping
```java
Stream<String> stream = list.stream()
	.map(String::toUpperCase);
	//[A,B,C]
    
    .map(Integers::parseInt);
    // 문자열 -> 정수로 변환
```
- 스트림 내 요소들을 하나씩 특정 값으로 변환하는 작업.
- 값 변환을 위한 람다를 인자로 받는다.
- 스트림의 값을 원하는 메소드에 입력값으로 넣고, 그 반환값을 담는다. 
3. Sorting
```java
Stream<String> stream = list.stream()
	.sorted() // [a,b,c] 오름차순 정렬
    .sorted(Comparator.reverseOrder()) // [c,b,a] (내림차순)
    
List<String> list = Arrays.asList("a","bb","ccc");
Stream<String> stream = list.stream()
	.sorted(Comparator.comparingInt(String::length)) // [ccc,bb,a] //문자열 길이 기준 정렬
```
- 스트림 내 요소 정렬.
- Comparator 사용 -> Comparator.reverseOrder() 등. 정확한 내용은 나중에 나오면.

4. 기타 연산
```java
Stream<String> stream = list.stream()
	.distinct() // 중복 제거
    .limit(max) // 최대 크기 제한
    .skip(n)    // 앞에서부터 n개 skip하기
    .peek(System.out::println) // 중간 작업결과 확인
```
- 그 외 다양한 연산

### 최종 연산
실제 결과값 리턴

이 시점에 중간 연산도 한 번에 실행된다.

1. Carculating
```java
IntStream stream = list.stream()
	.count()   //스트림 요소 개수 반환
    .sum()     //스트림 요소의 합 반환
    .min()     //스트림의 최소값 반환
    .max()     //스트림의 최대값 반환
    .average() //스트림의 평균값 반환
```
- 기본형 타입 사용시에만 쓸 수 있는 연산 메서드.
2. Reduction
```java
IntStream stream = IntStream.range(1,5);
	.reduce(10, (total,num)->total+num);
    //reduce(초기값, (누적 변수,요소)->수행문)
    // 10 + 1+2+3+4+5 = 25
```
- 스트림 요소를 하나씩 줄여가며 누적연산.
3. Collection
```java
//예시 리스트
List<Person> members = Arrays.asList(new Person("lee",26),
									 new Person("kim", 23),
									 new Person("park", 23));
                    
// toList() - 리스트로 반환
members.stream()
	.map(Person::getLastName)
    .collect(Collectors.toList());
    // [lee, kim, park]
    
// joining() - 작업 결과를 하나의 스트링으로 이어 붙이기
members.stream()
	.map(Person::getLastName)
    .collect(Collectors.joining(delimiter = "+" , prefix = "<", suffix = ">");
    // <lee+kim+park>
    
//groupingBy() - 그룹지어서 Map으로 반환
members.stream()
	.collect(Collectors.groupingBy(Person::getAge));
	// {26 = [Person{lastName="lee",age=26}],
    //  23 = [Person{lastName="kim",age=23},Person{lastName="park",age=23}]}
    
//collectingAndThen() - collecting 이후 추가 작업 수행
members.stream()
	.collect(Collectors.collectingAndThen (Collectors.toSet(),
    									   Collections::unmodifiableSet));
	//Set으로 collect한 후 수정불가한 set으로 변환하는 작업 실행
```
- .collect함수와 Collectors 메서드를 활용해 스트림의 요소를 원하는 자료형으로 변환.
4. Matching
```java
List<String> members = Arrays.asList("Lee", "Park", "Hwang");
boolean matchResult = members.stream()
						.anyMatch(members->members.contains("w")); //w를 포함하는 요소가 있는지, True

boolean matchResult = members.stream()
						.allMatch(members->members.length() >= 4); //모든 요소의 길이가 4 이상인지, False

boolean matchResult = members.stream()
						.noneMatch(members->members.endsWith("t")); //t로 끝나는 요소가 하나도 없는지, True
```
- 특정 조건을 만족하는 요소가 있는지 체크 결과 반환
- anyMatch, allMatch, noneMatch 택1
5. Iterating
```java
members.stream()
	.map(Person::getName)
    .forEach(System.out::println);
    //결과를 출력 (peek는 중간, forEach는 최종)

```
- .forEach로 스트림을 돌며 실행되는 작업.
6. Finding
```java
Person person = members.stream()
					.findAny()   //먼저 찾은 요소 하나 반환, 병렬 스트림의 경우 첫번째 요소가 보장되지 않음
                    .findFirst() //첫번째 요소 반환
```
- 스트림에서 하나의 요소 반환
- .find~ 메서드들.
## 특이사항
코드 가독성이 좋다.

for-loop보다 느림.



## 주요 메서드

### 중간 연산(Intermediate Operations) 메서드
| 메서드 | 설명 |
|------|-----|
| filter(Predicate<T>) | 조건(T/F)을 만족하는 요소만 통과시킴 |
| map(Function<T, R>) | 요소를 다른 형태/타입으로 변환 |
| mapToInt/Long/Double(...) | 객체 스트림을 기본형 특화 스트림으로 변환 |
| sorted() / sorted(Comparator) | 자연 순서 또는 지정한 기준으로 정렬 |
| distinct() | 중복 요소 제거 (equals 기준) |
| limit(long n) | 앞에서부터 n개만 남김 |
| skip(long n) | 앞에서 n개를 건너뜀 |
| peek(Consumer) | 중간에 요소를 들여다보기만 함 (디버깅용, 결과 변경 X) |
| flatMap(Function) | 중첩된 구조(예: 리스트의 리스트)를 평탄화 |

### 최종 연산(Terminal Operations) 메서드

| 메서드 | 설명 |
|------|-----|
| collect(Collector) | 리스트, 셋, 맵 등으로 결과 수집 |
| forEach(Consumer) | 각 요소에 대해 동작 수행 (반환값 없음) |
| count() | 요소 개수 반환 |
| sum(), average(), max(), min() | IntStream 등 기본형 스트림 전용 통계 메서드 |
| reduce(BinaryOperator) | 모든 요소를 하나의 값으로 누적/축약 |
| anyMatch(Predicate) | 하나라도 조건 만족하면 true |
| allMatch(Predicate) | 모두 조건 만족하면 true |
| noneMatch(Predicate) | 아무도 조건 만족 안 하면 true |
| findFirst() / findAny() | 조건에 맞는 요소 하나를 Optional로 반환 |
| toArray() | 배열로 변환 |

### 기타

자주 쓰는 Collertors -> 최종 연산 메서드인 collect와 함께 사용

| Collectors 메서드 | 설명 |
|------|-----|
| toList() | List로 수집 |
| toSet() | Set으로 수집 |
| joining(", ") | 문자열들을 구분자로 이어 붙임 |
| groupingBy(Function) | 특정 기준으로 그룹화 (Map 형태) |
| counting() | 개수 세기 (주로 groupingBy와 조합) |
| partitioningBy(Predicate) | true/false 두 그룹으로 분할 |

## 예시 문제

다음 코드를 완성해보세요:

> 정수 리스트가 주어질 때, 3의 배수만 골라서 그 합을 구하는 코드를 Stream API를 이용해 한 줄로 작성하시오.

```java
List<Integer> numbers = Arrays.asList(3, 5, 6, 8, 9, 12, 14);

int sum = numbers.stream()
        .filter(n -> n % 3 == 0)
        .reduce(0, (total, n) -> total + n);
        
System.out.println(sum); // 예상 결과: 30
```

```java
List<Integer> numbers = Arrays.asList(3, 5, 6, 8, 9, 12, 14);

int sum = numbers.stream()
        .filter(n -> n % 3 == 0)
        .mapToInt(n -> n) // mapToInt/Long/Double(...) : 객체 스트림을 기본형 특화 스트림으로 변환
        .sum(); 

System.out.println(sum); // 30
```
두 가지 방법.

## 첨언

lambda 작성법
python식은 django 프로젝트 문서에 작성된 파일 있음.[문서](/Python&Django/lambda_함수.md)

자바는 
```
(매개변수1, 매개변수2) -> 표현식
// 예시
(a, b) -> a + b
```
형태 사용.

