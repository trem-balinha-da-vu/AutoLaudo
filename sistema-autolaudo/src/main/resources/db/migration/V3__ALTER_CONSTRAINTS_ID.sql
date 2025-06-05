BEGIN;


ALTER TABLE usuario_permissao
  DROP CONSTRAINT IF EXISTS usuario_permissao_id_usuario_fkey;


-- 4.2) Drop da FK antiga: gerente_regional.id_perito → perito.id_usuario (antigo)
--       Agora perito já tem PK em perito.id, então vamos referenciar perito(id), não perito(id_usuario).
ALTER TABLE gerente_regional
  DROP CONSTRAINT IF EXISTS gerente_regional_id_usuario_fkey;

-- 4.1) Drop da PK antiga
ALTER TABLE gerente_regional
  DROP CONSTRAINT IF EXISTS gerente_regional_pkey;

-- 2.1) Drop da PK antiga (que havia sido criada sobre id, se for o caso)
ALTER TABLE perito
  DROP CONSTRAINT IF EXISTS perito_pkey;

-- 2.2) Drop da FK antiga (se ainda existia) que ligava perito.id_usuario → usuario.id
ALTER TABLE perito
  DROP CONSTRAINT IF EXISTS perito_id_usuario_fkey;

-- 3.1) Drop da PK antiga
ALTER TABLE administrador
  DROP CONSTRAINT IF EXISTS administrador_pkey;

-- 3.2) Drop da FK antiga: administrador.id_usuario → usuario.id
ALTER TABLE administrador
  DROP CONSTRAINT IF EXISTS administrador_id_usuario_fkey;


-- 1.1) Drop da constraint de PK antiga (em usuario)
ALTER TABLE usuario
  DROP  CONSTRAINT IF EXISTS usuario_pkey;

-- 1.2) Renomear coluna
ALTER TABLE usuario
  RENAME COLUMN id_usuario TO id;

-- 1.3) Recriar a PK sobre a nova coluna “id”
ALTER TABLE usuario
  ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);



-- 2.3) Adicionar coluna “id” como BIGSERIAL (auto‐incremental) e PK
ALTER TABLE perito
  ADD COLUMN id BIGSERIAL PRIMARY KEY;

-- 2.4) Recriar a FK: perito.id_usuario → usuario.id
ALTER TABLE perito
  ADD CONSTRAINT perito_id_usuario_fkey
    FOREIGN KEY (id_usuario)
    REFERENCES usuario(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE perito
  ADD CONSTRAINT perito_uuid_pk UNIQUE (uuid);


-- 3.3) Adicionar coluna “id” como BIGSERIAL PRIMARY KEY
ALTER TABLE administrador
  ADD COLUMN id BIGSERIAL PRIMARY KEY;

-- 3.4) Recriar FK: administrador.id_usuario → usuario.id
ALTER TABLE administrador
  ADD CONSTRAINT administrador_id_usuario_fkey
    FOREIGN KEY (id_usuario)
    REFERENCES usuario(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- 3.5) Garantir que uuid seja único
ALTER TABLE administrador
  ADD CONSTRAINT administrador_uuid_pk UNIQUE (uuid);


-- 4.3) Adicionar coluna “id” como BIGSERIAL PRIMARY KEY
ALTER TABLE gerente_regional
  ADD COLUMN id BIGSERIAL PRIMARY KEY;
ALTER TABLE gerente_regional
  RENAME COLUMN id_usuario TO id_perito;

-- 4.4) Recriar FK: gerente_regional.id_perito → perito.id
ALTER TABLE gerente_regional
  ADD CONSTRAINT gerente_regional_id_perito_fkey
    FOREIGN KEY (id_perito)
    REFERENCES perito(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- 4.5) Garantir que uuid seja único
ALTER TABLE gerente_regional
  ADD CONSTRAINT gerente_regional_uuid_pk UNIQUE (uuid);

-- 6.1) Drop de PK antiga (se existia)
ALTER TABLE usuario_permissao
  DROP CONSTRAINT IF EXISTS usuario_permissao_pkey;

ALTER TABLE usuario_permissao
  DROP CONSTRAINT IF EXISTS usuario_permissao_id_usuario_fkey;

-- 6.1) Drop de PK antiga (se existia)
ALTER TABLE usuario_permissao
  DROP CONSTRAINT IF EXISTS usuario_permissao_id_permissao_fkey;

-- 5.1) Drop da PK antiga
ALTER TABLE permissao
  DROP CONSTRAINT IF EXISTS permissao_pkey;

-- 5.2) Renomear coluna
ALTER TABLE permissao
  RENAME COLUMN id_permissao TO id;

-- 5.3) Recriar PK sobre “id”
ALTER TABLE permissao
  ADD CONSTRAINT permissao_pkey PRIMARY KEY (id);


-- 6.3) Renomear as colunas para nomes temporários
ALTER TABLE usuario_permissao
  RENAME COLUMN id_usuario     TO id_usuario_fk;
ALTER TABLE usuario_permissao
  RENAME COLUMN id_permissao   TO id_permissao_fk;

-- 6.4) Adicionar as FKs corretas:
ALTER TABLE usuario_permissao
  ADD CONSTRAINT usuario_permissao_id_usuario_fkey
    FOREIGN KEY (id_usuario_fk)
    REFERENCES usuario(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE usuario_permissao
  ADD CONSTRAINT usuario_permissao_id_permissao_fkey
    FOREIGN KEY (id_permissao_fk)
    REFERENCES permissao(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- 6.5) Recriar PK composta sobre (id_usuario_fk, id_permissao_fk)
ALTER TABLE usuario_permissao
  ADD CONSTRAINT usuario_permissao_pkey PRIMARY KEY (id_usuario_fk, id_permissao_fk);

-- 6.6) Renomear colunas temporárias de volta ao nome original
ALTER TABLE usuario_permissao
  RENAME COLUMN id_usuario_fk    TO id_usuario;
ALTER TABLE usuario_permissao
  RENAME COLUMN id_permissao_fk  TO id_permissao;


COMMIT;
