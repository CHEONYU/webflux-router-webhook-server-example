# WebFlux 웹훅 수신 애플리케이션

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-green?style=flat-square)
![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square)
![Kafka](https://img.shields.io/badge/Kafka-Enabled-orange?style=flat-square)

이 프로젝트는 **Spring WebFlux**를 기반으로 제작된 가벼운 **웹훅 수신 애플리케이션**입니다.  
수신된 웹훅 데이터를 비동기 방식으로 처리하며, 이를 메시지 큐인 **Kafka**에 퍼블리싱합니다.  
`Router Functional` 스타일로 설계되어 깔끔하고 효율적인 데이터 흐름을 제공합니다.

---

## 🚀 주요 기능

- **Reactive 웹훅 처리**:
    - `Spring WebFlux` 기반으로 높은 성능과 비동기 처리 제공.
- **Router Functional 스타일**:
    - 선언적인 방식으로 간결한 라우팅 제공.
- **Kafka 연동**:
    - 웹훅 데이터를 **Kafka Topic**에 퍼블리싱.
- **기술 스택**:
    - `Spring Boot 3`와 `Java 17`을 사용한 아키텍처.
- **비동기 데이터 흐름**:
    - 대량의 요청을 적은 리소스로 효율적으로 처리 가능.

---

## 🛠️ 기술 스택

- **Framework**: Spring Boot 3.x
- **Language**: Java 17
- **Reactive Programming**: Spring WebFlux
- **Messaging Queue**: Apache Kafka
- **Build Tool**: Gradle

## 🌐 동작 방식

1. **웹훅 수신**: 웹훅 이벤트를 특정 엔드포인트에서 수신합니다.
2. **데이터 검증**: 요청 데이터를 구조적으로 검증하고, 보안 체크를 수행합니다.
3. **Kafka 퍼블리싱**: 유효한 웹훅 데이터를 Kafka Topic에 전달합니다.
4. **비동기 처리**: 리액티브 스트림을 활용하여 높은 처리량을 효율적으로 소화합니다.

---

## 💬 문의

- 이메일: [devcheonyujung@gmail.com]
