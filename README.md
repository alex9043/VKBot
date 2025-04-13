# VKBot

Простой бот для вк, который должен копировать сообщение пользователя
и отправлять ему обратно в формате "Вы сказали: " + сообщение пользователя.

![img.png](img/TZ.png)

# Запуск

## Перед запуском

Для начала надо создать сообщество в ВК. Внутри сообщества надо перейти в **Управление->Работа с API**

На вкладке ключи доступа надо получить ключ доступа приложения.

**Создать ключ -> выбрать нужные права -> создать**

## VKTunnel

Для запуска на локальной машине может понадобиться подъем локального веб-сервера.
Можно использовать ngrok или VSCODE. Я выбрал VK Tunnel.
https://dev.vk.com/ru/libraries/tunnel

```bash
yarn global add @vkontakte/vk-tunnel
```

ИЛИ

```bash
npm install @vkontakte/vk-tunnel -g
```

После установки, можно изменить порт при желании на тот, который занимает приложение с ботом.

```bash
vk-tunnel --insecure=1 --http-protocol=http --ws-protocol=ws --host=localhost --port=5173 --timeout=5000
```

### Callback API

Далее надо перейти на вкладку Callback API в управлении сообществом и вставить адрес, который передал VKTunnel в поле "
адрес". **После запуска приложения, надо нажать кнопку подтвердить, чтобы вк знал, куда посылать запрос**

## Запуск при помощи DOCKER

Самый простой способ запустить приложение - при помощи docker-compose,
если установлен Docker Desktop ([Windows](https://docs.docker.com/desktop/setup/install/windows-install/),
[Linux](https://docs.docker.com/desktop/setup/install/linux/),
[Mac](https://docs.docker.com/desktop/setup/install/mac-install/)).

В файле docker-compose.yml надо указать в environment значения
VK_API_URL, VK_CALLBACK_API_SECRET, VK_CALLBACK_API_CONFIRM

И запустить команду:

```bash
docker compose up --build
```

Если пересобрать приложение с помощью maven, надо изменить путь в Dockerfile

## Запуск без DOCKER

### Запуск приложения

Перед непосредственным запуском надо установить значения в application.yml (src/main/resources/application.yml).
Также обязательно сменить порт на тот, что указан в VKTunnel

По умолчанию значения следующие:

- server.port=5173
- vk.api.url=https://api.vk.com/method/messages.send
- vk.callback.api.version=5.199
- vk.api.key=указан в ключах доступа при настройке работы с API
- vk.callback.api.secret=указан в поле секретный ключ в Callback API
- vk.callback.api.confirm=Строка, которую должен вернуть сервер в Callback API

```yml
spring:
  application:
    name: VKBot

server:
  port: ${SERVER_PORT}

vk:
  api:
    key: ${VK_API_KEY}
    url: ${VK_API_URL}
  callback:
    api:
      secret: ${VK_CALLBACK_API_SECRET}
      confirm: ${VK_CALLBACK_API_CONFIRM}
      version: ${VK_CALLBACK_API_VERSION}
```

Для запуска использовалась [OpenJDK 23](https://jdk.java.net/archive/).
Для начала надо собрать jar с помощью [maven](https://maven.apache.org/download.cgi)

```bash
mvn clean install -f pom.xml
```

Далее можно запустить jar

```bash
java -jar .\target\VKBot-0.0.1-SNAPSHOT.jar
```

# Возможности

Приложение может получать запрос от вк на подтверждение работы сервера и управлять ботом.

Управление ботом осуществляется за счет получение запроса от пользователя, создания ответа и отправки ответа
пользователю.