# GCD(최대공약수)

최대공약수 : A와 B의 공통된 약수 중 가장 큰 수

일반적으로 유클리드 호제법을 통해 구한다.

## 식(유클리드 호제법)

```java
public int gcd(int a, int b){
    if(b==0){
        return a;
    }
    else{
        return gcd(b, a%b)
    }
}
```

## 최소공배수
a와 b의 최소공배수 구하는 법

```java
int answer = a*b/gcd(a, b);
```

## 특이한 풀이
(최소공배수 문제)
```java
class Solution {
    public int solution(int n) {
        int answer = 1;

        while(true){
            if(6*answer%n==0) break; //루프를 통해 answer값을 1씩 늘려보며 '정답인 경우'를 찾는다.최소공배수이므로 6이나 n보다 큰 수부터 시작하면 좋을듯.
            answer++;
        }

        return answer;
    }
}
```