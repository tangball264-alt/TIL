# ORDER BY 절

조회된 결과를 특정 컬럼 기준으로 정렬한다.

SQL실행 순서상 가장 마지막에 실행됨.

디폴트 ASC(오름차순). 생략 가능.

내림차순 DESC

```SQL
SELECT MOVIE_NAME, RATING
  FROM MOVIE_INFO
 ORDER BY RATING DESC;
```
영화를 레이팅(10점 만점) 기준으로 값이 큰 것부터 점점 작은 것으로 정렬.

## 정렬 표현법

1. 일반 컬럼으로 정렬
- order by 컬럼명 [asc/desc]
2. 숫자로 정렬
- order by 3 [asc/desc]
- select에서 지정한 컬럼 중 앞에서부터 3번째 위치에 있는 컬럼을 기준으로 정렬.
- from의 테이블이 아니라, select의 컬럼임을 주의.
3. 혼용
- 2가지 컬럼을 기준으로 정렬할 수도 있다.
- 앞에 것 우선 - 동일한 순위에서 뒤에 것으로 비교.
- 이 때, 1번과 2번 혼용 가능.
4. 별칭으로 정렬
- SELECT에서 지정한 별칭(AS 별칭)으로 정렬
- order by 별칭 [asc/desc]
5. [case](함수.md#case-문법)문법 사용
```SQL
SELECT MOVIE_NAME, GENRE, RATING
  FROM MOVIE_INFO
 ORDER BY CASE GENRE WHEN 'SF' THEN 1
                     WHEN '액션' THEN 2
                     ELSE 3
          END, RATING DESC;
```
장르를 가지고 1,2,3 붙인 end값을 기준으로 정렬. end에는 asc, desc가 없으니 디폴트 asc(오름차순) 정렬
이제 같은 장르에서는 레이팅을 desc(큰 숫자부터)정렬.

## NULL 처리

DBMS마다 다르다.
| DBMS | **ASC(오름차순)** | **DESC(내림차순)** |
|------|-------------|-------------|
| **Oracle** | 마지막에 정렬 | 맨 앞에 정렬 |
| SQL Server | 맨 앞에 정렬 | 마지막에 정렬 |

단, Oracle에서는 order by 맨 마지막에 NULLS FIRST / NULLS LAST를 붙여 명시적인 정렬 위지 조정이 가능함.