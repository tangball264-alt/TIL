# Stack

## 개요

자바 Stack 클래스 (java.util.Stack)

후입선출(LIFO)구조를 구현한클래스.

## 기본 사용법

### 선언

```java
import java.util.Stack;

Stack<Integer> stack = new Stack<>();
```

### 주요 메서드

1. 넣기(push)
2. 맨 위 값 확인(제거x)(peek)
3. 맨 위 값 꺼내기(+제거)(pop)
4. 빈 스택인지 확인(isEmpty

```java
import java.util.Stack;

Stack<Integer> stack = new Stack<>();

stack.push(1);   // 1 넣기: [1]
stack.push(2);   // 2 넣기: [1, 2]
stack.push(3);   // 3 넣기: [1, 2, 3]

int top = stack.pop();   // 맨 위(가장 최근에 넣은 값) 꺼내면서 제거: 3 반환, 스택은 [1, 2]
int peek = stack.peek();  // 맨 위 값을 제거 없이 확인만: 2 반환, 스택은 그대로 [1, 2]

boolean empty = stack.isEmpty();  // 스택이 비어있는지 확인
```

## 대안

실무에서는 Deque를 더 권장.

```java
Deque<Integer> stack = new ArrayDeque<>();
```
스택과 같이 push, pop 사용.
