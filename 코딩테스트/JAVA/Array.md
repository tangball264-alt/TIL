# Array(배열)

## 특징

크기를 정하면 바꿀 수 없다.

선언 시 크기를 반드시 정함.

## 선언

1. 빈 배열 선언
```java
int[] intArray = new int[5];
String[] strArray = new String[4];
```

2. 선언하며 값 넣기
```java
int[] intArray = {90, 85, 100, 75};
String[] strArray = {"하나", "둘", "셋"};
```

3. 특수 형태
```java
// 선언과 생성을 따로 하는 경우.
int[] arr;
arr = new int[] {1, 2, 3, 4, 5};

// 메서드 호출 시 즉석에서 배열을 전달하는 경우(변수명이 없다)
printArray(new int[] {10, 20, 30});
```

+ 행렬 선언
```java
int[][] matrix = new int[행][열];
```

## 메서드

**Arrays. 계열 메서드는 java.util.*을 임포트하고 사용**

1. 정렬

- Arrays.sort(arr)

원본 배열을 정렬한다. 반환 없이 원본 배열을 수정.

*내림차순 정렬은 Collections.reverseOrder() 사용. int[]배열이 아닌 Integer[] 배열 전용*

```java
Integer[] arr = {3, 1, 4, 1, 5, 9};
Arrays.sort(arr, Collections.reverseOrder()); // [9, 5, 4, 3, 1, 1]
```
혹은  Arrays.stream(arr)로 stream형태로 전환 후 .boxed를 이용해 Integer형식으로 전환, 그 후 sorted()함수 사용 가능.


2. 출력

- Arrays.toString(arr) : 1차원 배열 출력
- Arrays.deepToString(arr) : 2차원 이상 다차원 배열 출력

System.out.println(arr) 사용 시 메모리 주소가 출력.

따라서 위 두 메서드를 이용해 배열의 전체 내용을 출력.

```java
int[] arr = {1, 2, 3};
System.out.println(Arrays.toString(arr)); // [1, 2, 3]

int[][] matrix = {{1, 2}, {3, 4}};
System.out.println(Arrays.deepToString(matrix)); // [[1, 2], [3, 4]]
```

3. 배열 복사

- Arrays.copyOf(arr, copylength) : 맨 처음부터 지정한 길이까지 복사하여 반환. copylength가 arr.length보다 크면 빈 공간은 0 혹은 null로 채운다.
- Arrays.copyOfRange(arr, startIndex, endIndex) : 지정 인덱스(시작)부터 지정 인덱스(끝) 전까지 복사하여 반환. 끝 인덱스 미포함.

4. 배열 초기화

- Arrays.fill(arr, value) : 지정 값으로 배열을 채운다.
- Arrays.fill(arr, startIndex, endIndex, value) : 지정한 시작 인덱스부터 끝 인덱스 전까지의 값을 지정한 값으로 채운다.

5. 이진 탐색

- Arrays.binarySearch()

배열에서 특정 값이 있는 인덱스를 고속으로 탐색.(정렬된 배열 한정)

정렬 안된 배열에서 특정 값의 index를 탐색하려면 리스트로 변환 후 리스트의 메서드인 .indexOf()를 사용하는 것이 좋다.

5. 배열 비교

- Arrays.equals()
- Arrays.deepEquals()

두 배열의 크기와 내부 요소들이 순서대로 모두 같은지 비교.(= 사용 시 주소값 비교함.)

6. 리스트로 변환

- Arays.asList()

고정 크기의 배열을 리스트 형태로 감싸 다룬다.

**크기 조절 불가. 따라서 add와 remove메서드 사용 불가**

## 내장 필드

- length

메서드가 아닌 필드(변수)이기에 괄호를 붙이지 않는다.

