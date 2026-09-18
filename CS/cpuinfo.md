# cpuinfo

## 개요

컴퓨터 환경을 확인하는 방법. 리눅스 계열 OS에서는 해당 파일을 열람한다.

## 리눅스

```cli
$ cat /proc/cpuinfo
```

Linux 커널이 관리하는 가상 파일 시스템인 /proc 내부의 cpuinfo 파일을 읽음으로써, 프로세서의 제조사, 모델명, 코어 수, 클럭 속도, 캐시 크기, CPU 기능(Flags) 등을 실시간으로 확인할 수 있습니다.

## 윈도우

**GUI** : 기본 내장 앱을 이용함. 작업 관리자와 시스템 정보 두 가지 앱이 있다.
- 작업 관리자 > 성능 탭 > CPU 항목 통해서 접근
- 시작 > 실행 창 > msinfo32 입력해 실행 > 프로세서 항목

**CLI** : 터미널 명령어를 통해 활용된다. 두 가지 방법이 있다.

```cmd
wmic cpu get Name, NumberOfCores, NumberOfLogicalProcessors
```
- 윈도우는 WMI 데이터베이스로 OS와 하드웨어 정보를 관리한다. 이 창고에 접근하여(wmic. 옛날 명령어), cpu클래스로 접근해 내 요청 정보(이름, 코어 수, 스레드 수)를 가져온다. 보안 및 성능상의 이유로 지원 중단이 진행중.

```cmd
Get-CimInstance CIM_Processor | Select-Object *
```
- 파웨쉘 환경에서 작동하는 최신 도구. 표준 규격인 CIM 기반으로 윈도우 커널이 인식한 CPU정보를 '디지털 객체'로 가져와(Get-CimInstance), 그 세부 속성을 나열(Select-Object *)한다.

## 맥OS

리눅스와 마찬가지로 Unix 기반이지만, /proc 파일 시스템 없음.

**GUI** : 애플 메뉴 > 이 Mac에 관하여 > 추가 정보 > 시스템 리포트 순으로 접근.

**CLI** : 터미널 명령어를 통하여 확인한다. 두 가지 방법이 있다.
```cmd
sysctl -n machdep.cpu.brand_string
```
- sysctl로 커널 내부 변수를 조회/수정한다. -n에 따라 변수명을 숨기고 값만을 출력한다. brand_string으로 cpu의 공식 모델명을 원한다는 것을 명시함. 따라서 '커널이 저장해둔 하드웨어 정보 중, cpu모델명만 출력'함.

```cmd
sysctl -a | grep machdep.cpu
```
- 전체 커널의 정보를 출력하되(sysctl -a), 수천 줄의 하드웨어 및 소프트웨어 설정값을 전부 보는 대신, 파이프라인(|)으로 machdep.cpu 라는 단어가 포함된 줄만 골라내(grep) 보여줌.


