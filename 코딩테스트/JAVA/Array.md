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
