# Api Finanças

<!---Esses são exemplos. Veja https://shields.io para outras pessoas ou para personalizar este conjunto de escudos. Você pode querer incluir dependências, status do projeto e informações de licença aqui--->

> Api que controla os gastos e ganhos
recebidos pelo usuário, dividido por categorias e subcategorias

### Ajustes e melhorias

O projeto é uma poc e será utilizado para testar conhecimentos em novas tecnologias. Próximas atualizações serão voltadas nas seguintes tarefas:

- [] Criar uma tabela usuário e vincular os gastos para cada um.
- [] Exportar balanço para planilhas e montar um consolidado.
- [] Criar um front para facilitar as operações.

## 💻 Pré-requisitos

* JDK 11
* Gradle 6.7+

OU:
* Docker Compose

## 🚀 Instalando api-financeiro

Para instalar o api-financeiro, siga estas etapas:

Para rodar na máquina
```
$ gradle build
$ ./gradlew bootRun
```

No docker
```
$ gradle build
$ cd docker/
$ docker-compose up -d
```

## ☕ Usando api-financeiro

Para usar api-financeiro, siga estas etapas:

Poderá acessar a aplicação clicando [aqui](https://financeiro-api-aann.herokuapp.com/api/swagger-ui/index.html)

Ou caso tenha rodado localmente [aqui](
http://localhost:8080/api/swagger-ui/index.html)

[⬆ Voltar ao topo](#nome-do-projeto)<br>