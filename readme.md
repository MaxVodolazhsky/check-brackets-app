# Check brackets application

## Getting started

Для локального запуска потребуется:

-[x] Открыть readme.md
- [ ] (По необходимости) в файле src/main/java/resources/application.yml - указать необходимый порт приложения (
  переменная: server.port)
- [ ] Запустить `gradle build`
- [ ] Выполнить команду для запуска приложения: `gradle bootRun`

### Проверка работоспособности

Проверить статус проекта можно следующим запросом:

`curl http://localhost:8080/actuator/health`

## OpenAPI

OpenApi-файл для генерации серверной части, размещается в директории `openapi/`