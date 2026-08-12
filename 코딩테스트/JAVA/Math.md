# Math

java.lang.Math 클래스. 일반적으로 Math 클래스로 지칭. java.lang 패키지에 속하기에 별도의 import를 요하지 않는다.

평균, 합산, 최댓값, 최솟값, 절댓값, 거듭제곱, 제곱근 등 다양한 수학적 연산 기능 제공.

## 주요 메서드

| 메서드 | 기능 | 특이사항 |
|---|---|---|
| **Math.abs(x)** | 절댓값 |  |
| **Math.max(a, b)** | 두 값 중 큰 값 |  |
| **Math.min(a, b)** | 두 값 중 작은 값 |  |
| Math.pow(base, exp) | 거듭제곱 (base의 exp제곱) | 반드시 double 반환 |
| **Math.sqrt(x)** | 제곱근 | 반드시 double 반환 |
| Math.cbrt(x) | 세제곱근 |  |
| Math.round(x) | 정수로 반올림 | float->int로, double->long으로 반환 |
| **Math.ceil(x)** | 정수로 올림 | double 반환 |
| Math.floor(x) | 정수로 내림 | double 반환 |
| Math.sin(x), Math.cos(x), Math.tan(x) | 삼각함수 | 인자는 도x 라디안o |
| Math.toRadians(deg) | 도(degree) → 라디안 변환 |  |
| Math.toDegrees(rad) | 라디안 → 도 변환 |  |
| Math.log(x), **Math.log10(x)** | 자연로그(밑이 e), 상용로그(밑이 10) |  |
| Math.exp(x) | e의 x제곱 |  |
| **Math.random()** | 0.0 이상 1.0 미만의 난수 반환 | double 반환 |

### 자주 쓰이는 조합

코딩테스트에서 자주 쓰이는 조합

| 목적 | 코드 |
|---|---|
| 두 수 중 최댓값/최솟값 | Math.max(), Math.min() |
| 절댓값(차이 계산) | Math.abs() |
| n의 제곱근까지 순회 | Math.sqrt(n) (이전 약수/소인수분해 문제에서 다룸) |
| 나눗셈 올림 처리 | Math.ceil((double)a/b) |
| 자릿수 세기 | (int)Math.log10(n)+1 또는 String.valueOf(n).length() |

## 상수

일부 수학적으로 중요한 상수를 제공한다.

| 이름 | 값 |
|---|---|
| Math.PI | 원주율 (약 3.14159...) |
| Math.E | 자연상수 e (약 2.71828...) |
