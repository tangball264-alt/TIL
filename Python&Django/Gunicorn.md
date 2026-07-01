# Gunicorn

Green Unicorn의 약칭. 통칭 구니콘.

Python 으로 작성된 WSGI(웹 서버 게이트웨이 인터페이스) HTTP 서버

Django, Flask등 파이썬 웹 애플리케이션이 외부의 클라이언트 요청을 처리할 수 있게 돕는다.

**즉, 웹 서버 소프트웨어(Nginx 등)와 파이썬 애플리케이션(장고 등)를 연결하는 역할.**

>명령어
>
>>pip install gunicorn
>>
>>gunicorn myapp:app
>
>https://gunicorn.org/


장점
1. 운영 환경에서 검증된 성능 : 여러 기업이 신뢰하고 사용. 
2. 경량화 : 최소한의 의존성과 단순한 설정
3. 높은 호환성 : 모든 WSGI또는 ASGI프레임워크와 호환됨.
