# 💡 프로젝트 소개

- 사용자들 마다 잔액을 관리할 수 있는 계좌시스템 구현
## 💡 개발 환경
<img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=Spring%20Boot&logoColor=black"/><img src="https://img.shields.io/badge/java-007396?style=flat-square&logo=java&logoColor=white"/><img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=flat-square&logo=apachetomcat&logoColor=black"/><img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white"/><img src="https://img.shields.io/badge/Postman-FF6C37?style=flat-square&logo=Postman&logoColor=white"/>
-  DB : H2 (memory DB 모드 활용) ,Embedded redis

## 💡 각 기능 별 구체적인 명세서 생성
### 계좌 
- 계좌 생성 API
    - POST/account
    - 파라미터: 사용자 아이디, 초기잔액
    - 정책
      - 사용자가 없는 경우
      - 계좌가 10개(사용자당 최대 보유 가능 계좌 수)인 경우 실패 응답
      - 성공 응답: 사용자 아이디, 계좌번호, 등록일시
     
- 계좌 해지 API
  - DELETE/account
  - 파라미터: 사용자 아이디, 계좌번호
  - 정책
    - 사용자 또는 계좌가 없는 경우
    - 사용자 아이디와 계좌 소유주가 다른 경우
    - 계좌가 이미 해지 상태인 경우
    - 잔액이 있는 경우 실패 응답
  - 성공 응답: 사용자 아이디, 계좌번호, 해지일시
 
- 계좌 확인 API
  - GET/account?user_id={usesrId}
  - 파라미터: 사용자 아이디
  - 정책
    - 사용자가 없는 경우 실패 응답
  - 성공응답: List<계좌번호,잔액>구조로 응답
 
### 거래정보
- 잔액 사용 API
  - POST/transaction/use
  - 파라미터: 사용자 아이디, 계좌번호, 거래금액
  - 정책
    - 사용자가 없는 경우
    - 사용자 아이디와 계좌 소유주가 다른 경우
    - 계좌가 이미 해지 상태인 경우
    - 거래금액이 잔액보다 큰 경우
    - 거래금액이 너무 작거나 큰 경우 실패 응답
      - 해당 계좌에서 거래(사용, 사용 취소)가 진행 중일떄 다른 거래 요청이 오는 경우 해당 거래가
        동시에 잘못 처리되는 것을 방지함
  - 성공응답: 계좌번호, 거래 결과 코드(성공/실패),거래 아이디, 거래금액,거래일시
 
- 잔액 사용 취소 API
  - POST/transaction/cancel
  - 파라미터: 거래 아이디, 취소 요청 금액
  - 정책
    - 거래 아이디에 해당하는 거래가 없는 경우
    - 거래금액과 거래 취소 금액이 다른 경우 실패응답
     - 1년이 넘은 거래는 사용 취소 불가능
    - 성공 응답: 계좌번호, 거래 결과 코드(성공/실패),거래 아이디, 거래금액, 거래일시
   
  - 거래 확인 API
    - GET/transaction/{transactionId}
    - 파라미터: 거래 아이디
    - 정책
      - 해당 거래 아이디의 거래가 없는 경우 실패 응답
    - 성공 응답: 계좌번호, 거래종류(잔액 사용, 잔액 사용 취소), 거래 결과 코드(성공/실패), 거래아이디,
       거래금액, 거래일시
      - 실패한 거래(사용/사용취소)도 거래를 확인할 수 있도록 함



