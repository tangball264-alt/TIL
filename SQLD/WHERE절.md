# WHERE 절

테이블에서 원하는 행만 필터링해 조회하도록 도와주는 SQL 문법.

## 문법과 사용 이유

### 데이터 조회 SQL의 실행 순서

1. 데이터를 조회하는 SQL들과 작성 순서(select, from 외에는 선택 사항)
```SQL
SELECT      -- 테이블에서 컬럼을 명시. 원하는 컬럼만 가져온다.
FROM        -- 데이터를 가져올 테이블을 지정한다.
WHERE       -- 테이블에서 조건을 명시해 원하는 튜플만 가져온다.
GROUP BY    -- 특정 컬럼을 기준으로 대상을 그룹화한다.
HAVING      -- 그륩화한 결과를 기준으로 원하는 튜플만 가져온다.
ORDER BY    -- 특정 컬럼을 기준으로 정렬해 재출력한다.
```
2. 실제 이 SQL들의 실행 순서
from -> where -> group by -> having -> select -> order by

### WHERE 절이 필요한 이유

내가 원하는 튜플(행)만 볼 수 있게 함으로써 불필요한 데이터를 제거하고, 빠른 결과를 반환해준다.

## 연산자
WHERE절에서 사용할 수 있는 조건 연산자.

### 비교/논리 연산자
1. 비교 연산자 : =, !=, >, <, >=, <= 등
2. 논리 연산자 : AND, OR
- 비교 연산자를 이용해 만든 조건문 여러 개를, 논리 연산자를 이용해 묶는다.
- 이 때, 논리 연산자가 여러 개 사용된다면 AND의 우선순위가 OR보다 높다. OR을 우선하고자 한다면 괄호를 이용한다.

예시
```SQL
SELECT EMP_ID, DEPT, SALARY
  FROM EMPLOYEE
 WHERE (DEPT = '인사팀'
    OR DEPT = '개발팀')
   AND SALARY >= 4000 ;
```
연봉 4000 이하의 인사팀, 개발팀 직원의 id, 부서, 연봉만을 추출하여 보기.(괄호로 or먼저 실행.)

만약 괄호가 없다면, 인사팀 직원, 혹은 연봉 4000이상의 개발팀 을 대상으로 함.(and먼저 실행됨.)

### 부정 연산자
NOT, !=, <>, ^= 등으로 표현. 이것들 중 어느 것을 써도 작동한다.

조건의 참/거짓을 반대로 변환한다.

```SQL
WHERE DEPT != '개발팀'
WHERE DEPT <> '개발팀'
WHERE DEPT ^= '개발팀'
WHERE NOT DEPT = '개발팀'
```
where는 조건문이 true인 튜플을 가져온다.
-> 개발팀 직원은 dept='개발팀'의 결과가 true다.
-> 부정 연산자(not)이 적용되었으니 결과는 false로 바뀐다
-> 따라서 이 직원의 정보는 가져오지 않는다.
-> 반대로, 인사팀 직원은 dept='개발팀'결과는 false다.
-> 부정 연산자 적용으로 결과는 true가 된다.
-> 따라서 이 직원의 튜플은 가져온다.

### NULL연산자
IS NULL, IS NOT NULL을 사용한다.(null은 '='비교가 불가능.)
```SQL
WHERE 컬럼명 IS NULL
  AND 컬럼명 IS NOT NULL;
```

### SQL 연산자
IN, BETWEEN, LIKE 등.

1. IN 연산자
- 여러 값을 동시에 비교할 때 사용.

-OR 조건의 조합과 같다.

```SQL
SELECT EMP_ID, EMP_NAME, DEPT
  FROM EMPLOYEE
 WHERE DEPT = '개발팀'
    OR DEPT = '영업팀';

SELECT EMP_ID, EMP_NAME, DEPT
  FROM EMPLOYEE
 WHERE DEPT IN ('개발팀', '영업팀');
```
두 내용은 동일하다.

- NOT과 함께 쓸 수도 있다.
- *단, NOT IN 뒤에 입력된 값 중 NULL이 포함되면 아무 데이터도 출력되지 않는다.*

2. BETWEEN 연산자
- 범위 조건을 간단하게 표현할 수 있다.
- 비교 연산의 AND와 같다.

```SQL
SELECT EMP_ID, EMP_NAME, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= 3500
   AND SALARY <= 4000 ;

SELECT EMP_ID, EMP_NAME, SALARY
  FROM EMPLOYEE
 WHERE SALARY BETWEEN 3500 AND 4000 ;
```
- 주의사항
> BETWEEN A OR B는 없음(무조건 AND)
>
> 무조건 ~이상 ~이하 개념(초과>, 미만< 불가)
>
> 이 조건에서 A는 B보다 작거나 같아야 함.(앞 값이 뒷 값보다 작을 것. 순서 틀리면 반환값 없음)

3. LIKE 연산자
문자열 패턴을 비교할 수 있으며 와일드카드 '%'와 '_'를 사용.

**와일드카드** : 특정한 의미를 지닌 기호. 단순 문자가 아닌 특수 조건을 가진 조건으로 해석됨.

1. '%' : 0개 이상 임의의 문자를 매칭. ('김%' = 김땡땡, 김땡, 김땡땡땡.... 전부.)
2. '_' : 정확히 1개의 문자 매칭. ('김_' = 김땡)

```SQL
SELECT EMP_ID, EMP_NAME
  FROM EMPLOYEE
 WHERE EMP_NAME LIKE '김%';
```
'김'으로 시작하는 이름
```SQL
SELECT EMP_ID, EMP_NAME
  FROM EMPLOYEE
 WHERE EMP_ID LIKE 'E__1';
```
id가 'E'로 시작하고, 마지막은 '1', 그리고 사이에 2 글자 있는 정보 찾기.

- like 조건은 대소문자를 구분한다.('E%'와 'e%'는 다르다.)
- not like는 조건으로 주어진 패턴이 아닌 데이터를 조회.

### 연산자 우선순위
1. 괄호 ()
2. 비교 연산자 =, >, <. >=
2. SQL 연산자 IN, BETWEEN, LIKE
2. NULL 연산자 IS NULL, IS NOT NULL
3. 부정연산자 NOT
4. AND
5. OR

## 날짜 조건 조회
실무/시험에서 자주 요구되는 작업.

where절과 형변환 함수를 함께 사용하여 수행한다.

```SQL
-- 2024년 1월 20일오후 1시부터 오후 3시 사이에 발급된 데이터를 조회하는 쿼리.
SELECT *
  FROM 증명서발급이력
 WHERE ISSUE_DT >=TO_DATE(202401201300)
   AND ISSUE_DT <= TO_DATE(202401201500)
```
혹은 비슷한 요구에 대하여 to_char을 사용하여 해결하면
```SQL
-- 2024년 1월 20일에 발급된 데이터 조회
SELECT *
  FROM 증명서발급이력
 WHERE TO_CHAR(ISSUE_DT, 'YYYYMMDD')=20240120
```
이 경우, '1시~1시 59분'사이 같은 경우는 가능해도 1시~3시는 손이 더 감. 그러니 그 경우 위와 같이 to_date 사용이 적합.

