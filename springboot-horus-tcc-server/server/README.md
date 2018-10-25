Orientações:

01. Faça o download e instalação do PostgreSQL;
02. Crie uma base de dados pelo pgAdmin chamada de "server";
03. Faça o download e instalação do IntelliJ IDEA Community;
04. No IntelliJ IDEA Community vá na opção "Import Project" selecione o arquivo "pom.xml" do projeto e carregue o mesmo;
05. Botão direito sobre a classe main ServerApplication e ir na opção "Run ServerApplication.main()";
06. O servidor rodará no seguinte path (URL): http://localhost:8080/server/precos/
07. Para salvar um novo preço enviar um método POST para o endereco http://localhost:8080/server/precos com o seguinte corpo:
{
  "produto": {
    "gtin": "1234567890123",
    "nome": "coca cola"
  },
  "fornecedor": {
    "documento": "12345678901234",
    "nome": "Mercado João"
  },
  "preco": 11
}
08. Para consultar todos os preços método GET para o endereco http://localhost:8080/server/precos
09. Para consultar por nome de produto (exemplo pesquisando por "coca") método GET para http://localhost:8080/server/precos/pesquisar-por-produto?nome=coca
10. Para consultar por nome de fornecedor (exemplo pesquisando por "Mercado") método GET para http://localhost:8080/server/precos/pesquisar-por-fornecedor?nome=mercado
11. O retorno das consultas sempre será um JSON no seguinte formato:
[
  {
    "produto": {
      "gtin": "1234567890123",
      "nome": "coca cola"
    },
    "fornecedor": {
      "documento": "12345678901234",
      "nome": "Mercado João"
    },
    "preco": 8
  },
   {
    "produto": {
      "gtin": "3445676543456",
      "nome": "pepsi"
    },
    "fornecedor": {
      "documento": "12345678901234",
      "nome": "Mercado João"
    },
    "preco": 6
  }
]