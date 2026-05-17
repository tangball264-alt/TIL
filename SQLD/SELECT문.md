# SELECT 문

SQL에서 데이터를 조회하는 데 사용.

DML(데이터 조작어)의 일종

6가지 키워드로 구성

## 구성요소

구문
- SELECT    : 출력할 컬럼 지정
- FROM      : 데이터를 가져올 테이블 지정
- WHERE     : 행(튜플) 필터링 조건 지정
- GROUP BY  : 특정 컬럼 기준으로 행을 그룹화
- HABING    : 그룹화된 결과에 조건 지정
- ORDER BY  : 결과를 특정 컬럼 기준 오름차순/내림차순 정렬(기본:오름차순)

## 기본 기능

```
SELECT STUDENT_NAME
FROM STUDENT ;
```

'학생'테이블의 '학생이름'컬럼(열)을 조회.

**;** 기호로 구문 종료.

여러 컬럼을 조회할 때는 쉼표(,) 사용.

## 기술

### 애스터리스크(*)

**대상 테이블의 모든 컬럼 조회**

*단, 실무에서는 사용이 권장되지 않는다.*
*이유 : 불필요한 컬럼 조회. 사용 컬럼 파악 어려움.*

### DISTINCT

**중복 제거**

```
SELECT DISTINCT MAJOR
FROM STUDENT ;
```
DISTINCT로 조회 결과에서 중복 데이터를 제거

학생 테이블에서 전공을 조회. 단, 중복을 제거하기 때문에, 한 학과당 하나만 남긴다.

단, 이 때 2개 이상의 컬럼을 조회하여 DISTINCT GENDER, MAJOR을 하면 **조회 컬럼들의 조합을 기준으로 중복 없이 출력**

즉, '여', '컴퓨터공학과'와 '남','컴퓨터공학과'는 통합되지 않고 별도 출력. 반대로 '여', '철학과'도 첫 번째 예시와 통합되지 않는다.

### ALL

**전체 표시**

```SQL
SELECT ALL MAJOR
FROM STUDENT
```
ALL로 중복을 제거하지 않고 전체 데이터를 그대로 조회한다.

기본값.

*이 ALL은 중복 제거 여부를 지정하는 키워드*

*비교 조건에서 사용하는 다중행 연산자 ALL과의 혼동에 주의*

### ALIAS(AS)

**출력 컬럼에 별칭 부여**

조회 시, 출력되는 컬럼명을 임의 지정.

```SQL
SELECT STUDENT_ID AS 학생ID
     , STUDENT_NAME AS 이름
FROM STUDENT;
```
실제 테이블의 컬럼명이 변경되는 것이 아님.


ALIAS 작성 시 주의사항
1. 문자 시작 : 특수문자/숫자로 시작할 수 없음
2. 예약어 금지 : select, from등 sql 예약어 사용 불가.
3. 허용 특수문자 : _, $, #만 허용.
4. 특수문자/공백 표함 : 공백이나 특수문자는 큰따옴표("")로 감싸 사용(3에 해당되지 않는 특수문자도 큰따옴표 하면 됨.)
5. AS 생략 : 선택 사항. 그냥 띄어쓰기 후 별칭 적어도 됨.
6. 자동 대문자화 : Oracle은 자동으로 대문자화함. 소문자 유지하려면 큰따옴표("") 사용.

```SQL
SELECT COL1 AS HI -- 가능
     , COL2 AS _HI -- 불가능
     , COL3 AS AS -- 불가능
     , COL4 AS HI_123$# -- 가능
     , COL5 AS "HI 123%^&" -- 가능
     , COL6 HI2 -- as 생략 가능
     , COL7 hello -- HELLO로 변환됨
     , COL8 "myCODE" -- myCODE 그대로 유지
FROM TAB1;
```

### 산술 연산자

SELECT에는 컬럼 입력, 리터럴, 컬럼-리터럴/컬럼-컬럼 간 사칙연산 등이 포함될 수 있다.

[*단, NULL이 연산에 포함되면 결과는 NULL*](Null속성.md#null)

**SELECT의 연산은 데이블 전체 데이터를 일괄처리 하는 게 아니라, 각 행마다 한번씩 반복수행되는 것.**


### 연결 연산자

**데이터를 이어붙이는 역할**


| DBMS | 기호 | 예시 |
| :--- | :---: | :--- |
| Oracle | \|\| | SELECT 이름 \|\| '님' FROM TAB1 |
| SQL Server | + | SELECT 이름+'님' FROM TAB1 |


*연결 연산에 NULL이 포함되면*

*Oracle : NULL을 무시하고 나머지를 연결*

*SQL server : NULL 출력*



## 자료형과 리터럴

### 정의

**리터럴** : 변수나 연산 결과가 아닌 "값" 그 자체

> 100 -> 숫자 리터럴
>
> "안녕" -> 문자 리터럴
>
> 2024-01-01 12:12:12 -> 날짜 리터럴

**자료형** : 이런 값(=리터럴)들을 구분하고 담을 수 있는 그릇.

> Oracle에서 자주 쓰는 자료형
>
> VARCHAR2(n) : 문자형. 최대 n바이트 지정.
>
> NUMBER : 숫자형. 양수, 음수, 소수 등
>
> DATE : 날짜형. 연-월-일, 연-월-일 시:분:초 등



VARCHAR이 아니라 VARCHAR2인 이유
> VARCHAR도 있음. 그러나, '만약에 SQL 국제 표준이 바뀌어 VARCHAR을 수정하면' 기존에 VARCHAR로 작성된 프로그램이 망가질  수 있다.
>
>현재 VARCHAR 데이터 형식은 VARCHAR2 데이터 형식과 동일합니다. 그러나 향후 Oracle 버전에서는 VARCHAR 데이터 형식이 다른 비교 의미 체계를 사용하여 가변 길이 문자열을 저장할 수 있게 될 수 있습니다. 따라서 가변 길이 문자열을 저장할 때는 VARCHAR2 데이터 형식을 사용하십시오.
>
> 출처 : https://docs.oracle.com/cd/A57673_01/DOC/server/doc/SCN73/ch6.htm#:~:text=The%20VARCHAR%20datatype%20is%20currently%20synonymous%20with,VARCHAR2%20datatype%20to%20store%20variable%2Dlength%20character%20strings.

### 중요성
RDBMS에서는 모든 컬럼에 자료형 지정 필수
또한 리터럴은 자료형과 일치해야 한다.

### 자료형 결정 시점
물리적 모델링
