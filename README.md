# PtBrLang-Compiler

## Tópicos de Ação
- [x] Definição da Linguagem
   - [x] Especificar a Gramática da Linguagem: Defina a sintaxe e semântica da sua linguagem personalizada, incluindo tipos de dados, estruturas de controle e operadores.
   - [ ] Documentação da Gramática: Crie uma documentação clara da gramática para referência futura durante a implementação.
- [ ] Iniciar um Projeto Spring Boot
   - [ ] Configuração do Projeto: Crie um novo projeto Spring Boot utilizando uma ferramenta como Spring Initializr. Configure as dependências necessárias (por exemplo, web, devtools).
   - [ ] Estruturação do Projeto: Organize o projeto em pacotes lógicos (controller, service, model, etc.).
- [ ] Criar uma Rota de Teste
   - [ ] Implementação de um Endpoint Hello World: Crie um endpoint REST que responda com "Hello World" para garantir que o servidor está configurado corretamente.
   - [ ] Testes da Rota: Verifique se o endpoint funciona corretamente via navegador ou ferramenta de testes como Postman.
- [ ] Leitura e Retorno de Arquivos
   - [ ] Implementação do Endpoint para Leitura de Arquivo: Crie uma rota que aceite upload de um arquivo .meucomp.
   - [ ] Processamento e Retorno de Arquivo: Configure o endpoint para simplesmente renomear o arquivo para .java e retornar ao cliente.
- [ ] Criar o Sistema de Compilação
   - [ ] Criação do Analisador Léxico
      - [ ] Definição dos Tokens: Use ANTLR para definir os tokens da sua linguagem.
      - [ ] Geração do Lexer: Gere o lexer usando ANTLR e integre-o no projeto.
   - [ ] Criação do Analisador Sintático
      - [ ] Definição das Regras Sintáticas: Defina as regras de parsing na gramática ANTLR.
      - [ ] Geração do Parser: Gere o parser usando ANTLR.
   - [ ] Criação do Analisador Semântico
      - [ ] Verificação de Tipos e Escopos: Implemente verificações de tipo e escopo baseadas na AST gerada pelo parser.
   - [ ] Criação do Gerador de Código
      - [ ] Geração de Código Java: Implemente a lógica para transformar a AST em código Java.
- [ ] Adicionar Suporte a Expressões e Operadores
   - [ ] Implementação do Avaliador de Expressões Aritméticas: Desenvolva a lógica para avaliar expressões aritméticas, respeitando a precedência dos operadores.
   - [ ] Implementação de Operadores Lógicos: Adicione suporte para operadores lógicos (AND, OR, NOT) na linguagem e no compilador.
---
## Gramática da Linguagem
```
# Programa
    Prog -> programa Declara Bloco fimprog ;
    
# Declarações
    Declara -> declare Ids ;
    Ids -> Id ( ',' Id )*

# Bloco de Comandos
    Bloco -> Cmds
    Cmds -> Cmd Cmds'
    Cmds' -> Cmd Cmds' | ε

# Comandos
    Cmd -> CmdLeitura | CmdEscrita | CmdExpr | CmdIf | CmdWhile | CmdDoWhile | CmdAtribuicao
    CmdLeitura -> leia ( Id ) ;
    CmdEscrita -> escreva ( Texto | Id ) ;
    CmdExpr -> Expr ;
    CmdIf -> se '(' Expr Op_rel Expr ')' entao '{' Cmds '}' SenaoOp
    SenaoOp -> senao '{' Cmds '}' | ε
    CmdWhile -> enquanto '(' Expr Op_rel Expr ')' faca '{' Cmds '}'
    CmdDoWhile -> faca '{' Cmds '}' enquanto '(' Expr Op_rel Expr ')' ;
    CmdAtribuicao -> Id = Expr ;

# Expressões
    Expr -> Termo Expr'
    Expr' -> '+' Termo Expr' | '-' Termo Expr' | ε
    Termo -> Fator Termo'
    Termo' -> '*' Fator Termo' | '/' Fator Termo' | ε
    Fator -> Num | Id | '(' Expr ')'
    
# Operadores Relacionais
    Op_rel -> '<' | '>' | '<=' | '>=' | '!=' | '=='

# Textos e Números
    Texto -> '"' (0..9 | a..z | A..Z | ' ')* '"'
    Num -> (0..9)+
    Id -> (a..z | A..Z) (a..z | A..Z | 0..9)*
```

