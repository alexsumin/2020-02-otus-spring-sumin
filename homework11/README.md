### Домашнее задание
#### Использовать WebFlux

Цель: разрабатывать Responsive и Resilent приложения на реактивном стеке Spring c помощью Spring Web Flux и Reactive Spring Data Repositories Результат: приложение на реактивном стеке Spring
1. За основу для выполнения работы можно взять ДЗ с Ajax + REST (классическое веб-приложение для этого лучше не использовать).
2. Но можно выбрать другую доменную модель (не библиотеку).
3. Необходимо использовать Reactive Spring Data Repositories.
4. В качестве БД лучше использовать MongoDB или Redis. Использовать PostgreSQL и экспериментальную R2DBC не рекомендуется.
5. RxJava vs Project Reactor - на Ваш вкус.
6. Вместо классического Spring MVC и embedded Web-сервера использовать WebFlux.
7. Опционально: реализовать на Functional Endpoints

Перед запуском приложения, если не mongodb не инсталлирована локально, можно запустить контейнер:  
`
docker pull mongodb  
docker run -d -p 27017-27019:27017-27019 --name mongodb mongo
`