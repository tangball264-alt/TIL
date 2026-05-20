# Q객체

Q 객체 : Django의 ORM에서 제공하는 복잡한 데이터베이스 쿼리를 생성하기 위한 클래스 (django.db.models.Q)

filter()등에서 사용하는 키워드 인자 쿼리 -> 기본적으로 AND

복잡한 쿼리(ex, OR문 포함) 실행 위해 사용.

## Django에서의 쿼리

데이터 모델을 만들 때, django는 자동적으로 database-abstraction API를 제공. 
이를 통해 오브젝트들을 만들고, 검색하고, 업데이트하며, 삭제한다.

1. Create
```python
from blog.models import Blog
b = Blog(name="Beatles Blog", tagline="All the latest Beatles news.")
b.save()
```
모델 클래스의 인스턴스를 생성하고, .save()로 데이터베이스에 반영


2. Update
```python
b5.name = "New name"
b5.save()
```


3. Search
모델 클래스의 Manager(django.db.models.manager.Manager)를 통해 QuerySet을 구성해야 함.

각 모델은 최소 하나의 Manager가 있으며, 이는 기본적으로 objects라고 불림.

즉, 모델의 매니저인 objects를 통해 QuerySet을 반환받는다. 이 QuerySet은 SQL의 SELECT문과 매칭된다.

```python
Entry.objects.filter(pub_date__year=2006)
#Entry 오브젝트 전체 중, pup_date의 year가 2006인 것만을 골라 보인다.
```
또한 WHERE절과 매칭되는 filter(), exclude()를 사용해 대상을 특정한다.

*이 외에도, 고유 데이터 한 건을 조회하기 위해 .get(pk=n)을 하거나, 데이터 정렬 및 개수 제한을 위해 .order_by('속성명')[n:m]으로 슬라이싱을 할 수도*

### 필드 조회 문법

SQL의 WHERE절을 채우는 세부 조건문과 같다.

filter()의 괄호 안에 들어간다.

**필드명__조회타입=값** 형태.

필드명 : 해당 모델에 있는 속성들. 

조회타입 : in, icontains등. 인스턴스의 필드 값이 '값' 리스트 중 하나에 포함되는지, 값을 포함하는지, 그보다 큰지, 작은지, 혹은 동일한지.

관계형 데이터베이스 조회를 위해서는 

**모델명__필드명__조회타입=값** 하기도.

-> 역방향 참조

filter에는 여러 조건이 들어갈 수 있으므로 쉼표로 구분하여 넣으면 된다. 단, 모든 조건은 AND로 적용.

## Q 객체를 이용한 조회
필터에서 조건을 넣을 때 OR이나 NOT조건을 구현하기 위해 Q객체를 캡슐화하여 사용.

```python
from django.db.models import Q

# 제목이 'Who'로 시작하거나, 또는 발매 연도가 2005년인 데이터 조회 (OR 조건)
Entry.objects.filter(Q(headline__startswith="Who") | Q(pub_date__year=2005))

# 제목이 'Who'로 시작하고, 발매 연도가 2005년이 '아닌' 데이터 조회 (NOT 조건 결합)
Entry.objects.filter(Q(headline__startswith="Who") & ~Q(pub_date__year=2005))
```

## 기타 내장 쿼리 모듈

- Q : filter의 한계인 WHERE절의 논리연산(or, not, xor 등)처리.
- F : 파이썬으로 데이터를 가져오지 않고 DB내에서 필드 값 비교/원자적 연산.
- Value : 파이썬의 상수값을 쿼리 표현식 내부에서 인식 가능하게 변환. F객체 등 다른 함수와 사용.
- ExpressionWrapper : F객체간 계산 시 데이터 타입 다름/신규 필드 타입 정의 등에서 결과물의 출력 타입을 강제 지정.
- Func : 각 RDBMS 시스템이 가진 고유 내장 함수를 직접 호출.
...