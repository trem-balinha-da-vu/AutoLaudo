--------------------------------------------------------------------------------
-- 1) Tabela PERFIL
--    → Contém os tipos de perfil que um usuário pode ter:
--      - PERITO
--      - GERENTE_REGIONAL
--      - ADMINISTRADOR
--------------------------------------------------------------------------------
CREATE TABLE perfil (
    id_perfil    SERIAL      PRIMARY KEY,
    nome_perfil  VARCHAR(30) NOT NULL UNIQUE
);

INSERT INTO perfil (nome_perfil) VALUES
  ('PERITO'),
  ('GERENTE_REGIONAL'),
  ('ADMINISTRADOR');

--------------------------------------------------------------------------------
-- 2) Tabela NIVEL_ACESSO
--    → Define níveis de acesso para administradores:
--      - TOTAL    (acesso irrestrito a toda funcionalidade)
--      - PARCIAL  (acesso restrito a algumas operações, ex.: só leitura ou só aprovações limitadas)
--------------------------------------------------------------------------------
CREATE TABLE nivel_acesso (
    id_nivel     SERIAL      PRIMARY KEY,
    descricao    VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO nivel_acesso (descricao) VALUES
  ('TOTAL'),
  ('PARCIAL');

--------------------------------------------------------------------------------
-- 3) Tabela USUARIO
--    → Armazena dados básicos de qualquer usuário do sistema.
--    → FK para PERFIL (qual “papel” esse usuário desempenha).
--    → A senha fica em "senha_hash" usando algoritmo bcrypt (ou Argon2) no backend.
--------------------------------------------------------------------------------
CREATE TABLE usuario (
    id_usuario   SERIAL        PRIMARY KEY,
    nome         VARCHAR(150)  NOT NULL,
    email        VARCHAR(150)  NOT NULL UNIQUE,
    senha_hash   VARCHAR(200)  NOT NULL,
      -- Exemplo de conteúdo real:
      --   '$2a$10$7dQeH9MkzjDFG7iP8u2vQOmXbC6d9hZ/3YJpRIXka9LvYF4dA6L7a'
      -- (hash gerado pelo BCryptPasswordEncoder, tamanho ~60-70 caracteres;
      --  aqui alocamos 200 para ter margem)
    id_perfil    INTEGER       NOT NULL
      REFERENCES perfil(id_perfil)
      ON DELETE RESTRICT
      ON UPDATE CASCADE
);

--------------------------------------------------------------------------------
-- 4) Tabela PERITO
--    → Tabela especializada para armazenar dados adicionais de um usuário
--      que tenha perfil = 'PERITO'.
--    → Cada perito é um usuário que já está em USUARIO com id_perfil apontando para 'PERITO'.
--    → Exemplo de campos reais e significados:
--       • crm_perito          → Registro profissional do perito (ex.: registro no órgão de perícia).
--       • area_especialidade  → Área de atuação do perito (Ex.: “Exame de Acidente de Trânsito”,
--                              “Perícia Criminal”, “Perícia Ambiental”).
--       • nivel_experiencia   → Ex.: 'Júnior', 'Pleno', 'Sênior'.
--       • telefone_contato    → Número de telefone do perito.
--       • ativo               → Se ainda está ativo (TRUE / FALSE).
--------------------------------------------------------------------------------
CREATE TABLE perito (
    id_usuario          INTEGER       PRIMARY KEY
      REFERENCES usuario(id_usuario)
      ON DELETE CASCADE
      ON UPDATE CASCADE,

    crm_perito          VARCHAR(50)   NOT NULL UNIQUE,
    area_especialidade  VARCHAR(100)  NOT NULL,
    nivel_experiencia   VARCHAR(20)   NOT NULL,
    telefone_contato    VARCHAR(20),
    ativo               BOOLEAN       NOT NULL DEFAULT TRUE
);


--------------------------------------------------------------------------------
-- 5) Tabela GERENTE_REGIONAL
--    → Tabela especializada com dados adicionais de um gerente regional.
--    → Cada gerente é um perito, que por sua vez é um usuário, mas com com id_perfil → 'GERENTE_REGIONAL'.
--    → O campo "regional" indica a área geográfica pela qual o gerente
--      será responsável (Ex.: "Sul", "Sudeste", "Norte", ou “Região Metropolitana de SP”,
--      ou “Região 01 – RS”).
--    → Exemplo de uso em um caso real:
--       Se “regional = 'Itabuna'”, esse gerente só vai gerenciar os peritos de Itabuna.
--------------------------------------------------------------------------------
CREATE TABLE gerente_regional (
    id_usuario   INTEGER       PRIMARY KEY
      REFERENCES perito(id_usuario)
      ON DELETE CASCADE
      ON UPDATE CASCADE,

    regional     VARCHAR(100)  NOT NULL
      -- Exemplos: 'Sul', 'Sudeste', 'Norte', 'Região Metropolitana de SP', etc.
);

--------------------------------------------------------------------------------
-- 6) Tabela ADMINISTRADOR
--    → Tabela especializada para armazenar dados extras de um administrador.
--    → Cada administrador é um usuário com id_perfil → 'ADMINISTRADOR'.
--    → "id_nivel" referencia a tabela nivel_acesso, definindo se o admin
--      tem permissão TOTAL ou PARCIAL.
--    → "departamento" exemplifica que, na prática, um administrador pode
--       pertencer a um setor (Ex.: “TI”, “Operacional”, “Financeiro”).
--------------------------------------------------------------------------------
CREATE TABLE administrador (
    id_usuario    INTEGER       PRIMARY KEY
      REFERENCES usuario(id_usuario)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
    id_nivel      INTEGER       NOT NULL
      REFERENCES nivel_acesso(id_nivel)
      ON DELETE RESTRICT
      ON UPDATE CASCADE
);

--------------------------------------------------------------------------------
-- 7) Tabela PERMISSAO
--    → Lista de permissões (ações) que podem ser atribuídas a usuários.
--    → Exemplo de valores reais:
--       'CRIAR_LAUDO', 'APROVAR_LAUDO', 'GESTAO_USUARIOS', 'VISUALIZAR_RELATORIOS' etc.
--------------------------------------------------------------------------------
CREATE TABLE permissao (
    id_permissao   SERIAL       PRIMARY KEY,
    nome_permissao VARCHAR(50)  NOT NULL UNIQUE
);

--------------------------------------------------------------------------------
-- 8) Tabela USUARIO_PERMISSAO
--    → Mapeia quais permissões cada usuário tem (N:N entre USUARIO e PERMISSAO).
--------------------------------------------------------------------------------
CREATE TABLE usuario_permissao (
    id_usuario     INTEGER      NOT NULL
      REFERENCES usuario(id_usuario)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
    id_permissao   INTEGER      NOT NULL
      REFERENCES permissao(id_permissao)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
    PRIMARY KEY (id_usuario, id_permissao)
);

-- 6) Definir algumas permissões básicas
INSERT INTO permissao (nome_permissao) VALUES
  ('GERAR_RELATÓRIO_INDIVIDUAL'),
  ('GERAR_RELATÓRIO_EQUIPE'),
  ('CRIAR_LAUDO'),
  ('VALIDAR_LAUDO'),
  ('VISUALIZAR_DASHBOARD_INDIVIDUAL'),
  ('VISUALIZAR_DASHBOARD_EQUIPE'),
  ('GERIR_USUARIOS');


