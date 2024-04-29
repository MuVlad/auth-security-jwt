<div>

## Auth-Security-JWT

Базовое веб-приложение с использованием Spring Security и JWT для аутентификации и авторизации пользователей

</div>

---

### Разработчик

- [Муслимов Владислав](https://github.com/MuVlad)
- Контактная информация: *[muslimov.vlad@mail.ru]()*

---

## Запуск приложения

Для запуска приложения Вам потребуется выполнить несколько шагов:

1. Клонировать проект и открыть его в среде разработки (например, *IntelliJ IDEA* или *VSCode*);
2. В файле **docker-compose.yml** выполнить инструкции по запуску базы данных PostgreSQL;
3. В терминале перейдите в каталог проекта и выполните следующую команду для сборки и запуска приложения:
```bash
./gradlew bootRun
```

Swagger будет доступен по адресу: http://localhost:8080/swagger-ui/index.html

---

## Стэк технологий

* **Backend**:
    - Java 17
    - Gradle
    - Spring Boot
    - Spring Web
    - Spring Security
    - Spring Data JPA
    - Swagger
    - Lombok


* **SQL**:
    - PostgreSQL


* **Tests**:
    - JUnit
    - Mockito
    - Testcontainers