# 람다(Lambda)함수

> ### lambda
>
> An anonymous inline function consisting of a single expression which is evaluated when the function is called. The syntax to create a lambda function is ```lambda [parameters]: expression```
>
> **익명 인라인 함수 람다. 하나의 표현식으로 이루어져 있으며 함수가 호출될 때 그 표현식이 평가된다.**
>
> **람다 함수를 만드는 문법은 다음과 같다.**
> ```
> lambda [parameters]: expression
> ```
*출처 : https://docs.python.org/3/glossary.html#term-lambda*


익명 함수 : 식별자(이름)를 갖지 않는 함수.

일반적인 함수는 ```def 이름(매개변수):```형태로 선언 후 호출되지만, 람다함수는 lambda 키워드를 사용해 한 줄로 간결히 표현된다.


특징

1. 이름이 없고
2. 표현식 단위로 인라인 정의되며
3. 단일 행 표현식만을 허용하고
4. 실행 후 즉시 소멸된다.
5. 간단한 연산 및 일시적 사용에 적합하다.


이점

1. 코드의 휘발성과 간결성
 - 일회용 성격을 띄어 메모리 효율에 이득을 볼 수 있다.
2. 함수형 프로그래밍과의 결합
 - 함수를 인자로 받는 일부 내장 함수(map(), filter(), reduce() 등)를 사용할 때, 별도의 함수 선언 없이 즉석에서 로직을 주입할 수 있어 데이터 처리 흐름이 유연해짐.
3. 고차 함수의 표현
 - 함수 내부에서 다른 함수를 반환하거나 인자로 넘길 때, 람다로 구조 단순화 가능.


형식

```lambda 매개변수1, 매개변수2, ... : 표현식```

**파이썬 람다 작성법**

예시

>```lambda x: x**2```
>x를 입력받아 그 제곱값을 반환한다.

>```lambda s: s['score']```
>딕셔너리 s를 받아 그 'score'키의 value값을 반환한다.

단순 형식 외에 사용처에 맞게 코드를 작성하는 연습이 필요할 듯.


단점
1. 오남용 시 오히려 가독성 저해 가능
2. 디버깅 시, 함수 이름이 나타나지 않아 추적 어려움.
3. 공식 가이드에서는 람다를 변수에 할당하기보다는 def로 사용할 것 권고(인자로 직접 전달할 때 한정하여 쓸 것)