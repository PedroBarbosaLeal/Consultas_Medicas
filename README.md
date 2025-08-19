✨ Sistema de Agendamento de Consultas Médicas
💻 Sobre o Projeto
API RESTful desenvolvida em Java com Spring Boot para o gerenciamento completo de consultas, pacientes e médicos. O projeto foca em uma arquitetura robusta e na integridade de dados para simular o backend de um sistema de agendamento real.

🚀 Funcionalidades
✔️  Cadastro e Gestão: Endpoints para cadastrar pacientes, médicos e agendar consultas.

🛡️  Validação de Dados: Utilização de validações Jakarta para garantir que as requisições atendam aos requisitos de negócio.

🔍  Queries Derivadas: Implementação de buscas personalizadas (ex: listar consultas por médico) usando as convenções do Spring Data JPA.

🔗  Mapeamento de Entidades: Relacionamentos entre as entidades (Paciente, Medico, Consulta) modelados com JPA.

🛠️ Tecnologias Utilizadas
Linguagem: Java

Framework: Spring Boot

Persistência: Spring Data JPA, Hibernate

Banco de Dados: PostgreSQL

Gerenciamento de Dependências: Maven

Outros: Lombok, APIs RESTful, Postman/Insomnia

📂 Como Rodar o Projeto
Clone o repositório: git clone https://github.com/PedroBarbosaLeal/Consultas_Medicas.git

Abra o projeto na sua IDE (IntelliJ, VS Code, etc.).

Configure as credenciais do seu banco de dados PostgreSQL no arquivo src/main/resources/application.properties.

Execute a classe principal ConsultasMedicasApplication.

📌 Endpoints da API
A API está configurada para funcionar na porta 8080 e organizada pelas entidades principais.

Endpoints de Pacientes 

Método	Endpoint	Descrição
POST	/pacientes	
Cadastra um novo paciente 

GET	/pacientes	
Lista todos os pacientes 

GET	/pacientes/sort/asc	
Lista pacientes ordenados por data de nascimento (ascendente) 

GET	/pacientes/sort/desc	
Lista pacientes ordenados por data de nascimento (descendente) 

PUT	/pacientes/{id}	
Atualiza um paciente existente 

DEL	/pacientes/{id}	Deleta um paciente

Endpoints de Médicos 

Método	Endpoint	Descrição
POST	/medicos	
Cadastra um novo médico 

GET	/medicos	
Lista todos os médicos 

PUT	/medicos/{id}	
Atualiza um médico existente 

DEL	/medicos/{id}	
Deleta um médico 

Endpoints de Consultas 

Método	Endpoint	Descrição
POST	/consultas	
Agenda uma nova consulta 

GET	/consultas	
Lista todas as consultas 

GET	/consultas/pacientes/{id}	
Lista todas as consultas de um paciente específico 

GET	/consultas/medicos/{id}	
Lista todas as consultas de um médico específico 

GET	/consultas/sort/asc	
Lista consultas ordenadas por data (ascendente) 

GET	/consultas/sort/desc	
Lista consultas ordenadas por data (descendente) 

PUT	/consultas/{id}	
Atualiza uma consulta existente 

DEL	/consultas/{id}	
Deleta uma consulta 
