Este projeto consiste na implementação de uma API RESTful completa para a plataforma fictícia Serratec Music, gerenciando usuários, perfis, artistas, músicas e playlists, 
conforme os requisitos do trabalho individual da disciplina. A API é desenvolvida utilizando Spring Boot 3, JPA/Hibernate e conectada ao banco de dados PostgreSQL.

 Desenvolvedor: Lucca Zappala Jurado

⚙️ Tecnologias Utilizadas
Linguagem: Java 17+

Framework: Spring Boot 3

Banco de Dados: PostgreSQL

Persistência: Spring Data JPA / Hibernate

Validação: Bean Validation (jakarta.validation)

Documentação: Springdoc OpenAPI (Swagger UI)

📂 Arquitetura do Projeto
O projeto segue a arquitetura solicitada, com a lógica de negócio contida diretamente na camada de Controller, comunicando-se com a camada de Repository.

domain: contém todas as entidades (Usuario, Playlist, Musica, etc.).

repository: interfaces para acesso ao banco de dados (Spring Data JPA).

controller: camada REST que expõe os endpoints.

exception: contém o tratamento de exceções centralizado (@ControllerAdvice).

config: contém a classa OpenAPIconfi, classe de configuração do spring boot.

enums: contém o enum GeneroMusica para definir os gêneros musicais.

🚀 Instruções de Execução
Siga os passos abaixo para colocar a aplicação em funcionamento:

1. Configuração do Banco de Dados
A aplicação está configurada para se conectar ao PostgreSQL.

Crie um banco de dados com o nome desejado (ex: serratecmusic).

Edite o arquivo src/main/resources/application.properties (ou application.yml) e substitua as credenciais pelas suas:

Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/serratecmusic
spring.datasource.username=seu_usuario_postgres
spring.datasource.password=sua_senha_postgres
spring.jpa.hibernate.ddl-auto=update
2. Inicialização da API
Clone este repositório para sua máquina local.

Abra o projeto na sua IDE.

Execute a classe principal que contém o método main() (ex: SerratecMusicApplication.java).

A API será iniciada na porta padrão 8080.

3. Acesso e Documentação (Swagger UI)
Após a inicialização, a documentação interativa estará acessível:

🔗 Swagger UI URL: http://localhost:8080/swagger-ui.html

4. Teste Inicial (POST Aninhado de Usuário)
O requisito de persistência aninhada (Usuario com Perfil e Playlist) foi implementado com sucesso. Para testar o endpoint principal, faça uma requisição POST para http://localhost:8080/usuarios com o seguinte corpo JSON:

Método: POST URL: http://localhost:8080/usuarios Body (JSON):

JSON

{
  "nome": "Lucca Zappala Jurado",
  "email": "luccazappala@gmail.com",
  "perfil": {
    "telefone": "993236682",
    "dataNascimento": "2001-09-05" 
    // Data de nascimento está no passado para passar na validação @Past
  },
  "playlists": [
    {
      "nome": "Relax",
      "descricao": "Músicas para relaxar a cabeça depois de passar horas fazendo API"
    }
  ]
}
