# URL 경로 컨버터

Django의 path()함수는 주소 패턴을 직관적으로 캡쳐하기 위해 경로 컨버터 문법(<타입:변수명>)을 사용.

## 특징
**자동 형변환** : URL문자열을 매칭하는 동시에 지정된 타입으로 변환해 view함수에 인자로 제공.

**매칭 제한** : 해당 컨버터의 데이터 형식 규칙과 일치하는 요청만 수용. 불일치 시 다음 URL 패턴으로 넘어가거나 404 에러 발생시킴.

## 컨버터 종류

### 기본 제공 컨버터

| 컨버터 이름 | 매칭 규칙 | 반환 데이터 타입 | 주요 용도 및 특징 |
| :--- | :--- | :--- | :--- |
| **`str`** | `/`를 제외한 비어 있지 않은 문자열 | `str` (문자열) | **기본값** (컨버터 생략 시 자동 적용). 단어 단위 매칭. |
| **`int`** | 0을 포함한 양의 정수 | `int` (정수) | 데이터베이스의 고유 ID(`pk`) 값을 조회할 때 필수 사용. |
| **`slug`** | 알파벳, 숫자, 하이픈(`-`), 언더스코어(`_`) | `str` (문자열) | 포스트 제목을 주소창에 그대로 쓸 때 사용 (예: `/blog/django-setup/`). |
| **`uuid`** | 하이픈을 포함한 소문자 UUID 형식 | `UUID` 인스턴스 | 유추하기 어려운 고유 주소나 보안용 고유 식별자 처리 시 사용. |
| **`path`** | **`/`를 포함한** 비어 있지 않은 모든 문자열 | `str` (문자열) | URL 세그먼트가 아닌, **전체 경로 덩어리**를 통째로 캡처할 때 사용. |

>Path converters
>
>The following path converters are available by default:
>
>str - Matches any non-empty string, excluding the path separator, '/'. This is the default if a converter isn’t included in the expression.
>
>int - Matches zero or any positive integer. Returns an int.
>
>slug - Matches any slug string consisting of ASCII letters or numbers, plus the hyphen and underscore characters. For example, building-your-1st-django-site.
>
>uuid - Matches a formatted UUID. To prevent multiple URLs from mapping to the same page, dashes must be included and letters must be lowercase. For example, 075194d3-6885-417e-a8a8-6c931e272f00. Returns a UUID instance.
>
>path - Matches any non-empty string, including the path separator, '/'. This allows you to match against a complete URL path rather than a segment of a URL path as with str.
>
> *출처 : [django 공식 문서](https://docs.djangoproject.com/en/6.0/topics/http/urls/#path-converters)*

### 커스텀 컨버터

직접 파이썬 클래스를 정의해 컨버터를 만들 수 있다.

요구사항

1. regex : 매칭할 정규 표현식 문자열 속성
2. to_python(self, value) : 주소창의 문자열을 파이썬 데이터 타입으로 변환해 뷰로 전달하는 메서드
3. to_url(self, value) : 파이썬 데이터 타입을 다시 URL 주소 문자열로 역변환하는 메서드

> 예시
> ```python
># 네 자리 연도(YYYY)만 정밀 매칭하는 커스텀 컨버터 예시
>class FourDigitYearConverter:
>    regex = "[0-9]{4}"
>
>    def to_python(self, value):
>        return int(value)  # 뷰로 넘겨줄 때 정수로 변환
>
>    def to_url(self, value):
>        return "%04d" % value  # 주소를 생성할 때 4자리 문자열로 포맷팅
>```
> 이렇게 정의한 후, django.urls.register_converter()함수를 이용해 urls.py에서 사용한다.
> ```python
>from django.urls import path, register_converter
>from . import converters, views
>
># 'yyyy'라는 이름으로 커스텀 컨버터 등록
>register_converter(converters.FourDigitYearConverter, >"yyyy")
>
>urlpatterns = [
>    # 이제 내장 컨버터처럼 URL 패턴에 등록하여 사용 가능
>    path("articles/<yyyy:year>/", views.year_archive),
>]
>```