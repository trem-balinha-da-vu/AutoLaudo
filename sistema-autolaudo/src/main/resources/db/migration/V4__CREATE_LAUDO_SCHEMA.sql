-- 1) Tabela veiculo
CREATE TABLE veiculo (
    placa VARCHAR(20) PRIMARY KEY,
    chassi VARCHAR(30),
    renavam VARCHAR(30),
    modelo VARCHAR(100),
    ano_fabricacao INTEGER,
    status_registro VARCHAR(20)
);

-- 2) Tabela norma
CREATE TABLE norma (
    id_norma SERIAL PRIMARY KEY,
    nome_norma VARCHAR(255),
    tipo VARCHAR(20),
    texto_norma TEXT
);

-- 3) Tabela template_laudo
CREATE TABLE template_laudo (
    id_template SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    versao VARCHAR(20)
);

-- 4) Tabela selo_digital (precisa ser antes de laudo_veicular)
CREATE TABLE selo_digital (
    id_selo SERIAL PRIMARY KEY,
    data_emissao TIMESTAMP,
    autoridade_emissora VARCHAR(255),
    hash_conformidade VARCHAR(255)
);

-- 5) Tabela formulario_laudo (precisa de template_laudo)
CREATE TABLE formulario_laudo (
    id_formulario SERIAL PRIMARY KEY,
    modo_offline BOOLEAN,
    id_template INTEGER REFERENCES template_laudo(id_template)
);

-- 6) Tabela laudo_veicular (precisa de veiculo, selo_digital, formulario_laudo, usuario)
CREATE TABLE laudo_veicular (
    id_laudo SERIAL PRIMARY KEY,
    data_criacao TIMESTAMP NOT NULL,
    data_emissao TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    tipo_laudo VARCHAR(50),
    descricao_problema TEXT,
    local_troca VARCHAR(255),
    detalhes_manutencao TEXT,
    observacoes TEXT,
    hash_documento VARCHAR(255),
    id_selo INTEGER REFERENCES selo_digital(id_selo),
    id_formulario INTEGER REFERENCES formulario_laudo(id_formulario),
    placa_veiculo VARCHAR(20) REFERENCES veiculo(placa),
    id_perito INTEGER REFERENCES usuario(id)
);

-- 7) Tabela form_laudo_campo (precisa de formulario_laudo)
CREATE TABLE form_laudo_campo (
    id_formulario INTEGER,
    nome_campo VARCHAR(255),
    valor TEXT,
    PRIMARY KEY (id_formulario, nome_campo),
    FOREIGN KEY (id_formulario) REFERENCES formulario_laudo(id_formulario)
);

-- 8) Tabela laudo_norma (depende de laudo_veicular e norma)
CREATE TABLE laudo_norma (
    id_laudo INTEGER,
    id_norma INTEGER,
    PRIMARY KEY (id_laudo, id_norma),
    FOREIGN KEY (id_laudo) REFERENCES laudo_veicular(id_laudo),
    FOREIGN KEY (id_norma) REFERENCES norma(id_norma)
);

-- 9) Tabela imagem (depende de laudo_veicular)
CREATE TABLE imagem (
    id_imagem SERIAL PRIMARY KEY,
    caminho VARCHAR(255),
    legenda VARCHAR(255),
    tipo VARCHAR(50),
    id_laudo INTEGER REFERENCES laudo_veicular(id_laudo)
);

-- 10) Tabela historico_alteracao (depende de usuario e laudo_veicular)
CREATE TABLE historico_alteracao (
    id_hist SERIAL PRIMARY KEY,
    data_alteracao TIMESTAMP,
    descricao TEXT,
    id_usuario INTEGER REFERENCES usuario(id),
    id_laudo INTEGER REFERENCES laudo_veicular(id_laudo)
);

-- 11) Tabela sincronizador (depende de usuario e laudo_veicular)
CREATE TABLE sincronizador (
    id_sync SERIAL PRIMARY KEY,
    data_sync TIMESTAMP,
    id_usuario INTEGER REFERENCES usuario(id),
    id_laudo INTEGER REFERENCES laudo_veicular(id_laudo)
);

-- 12) Tabela integracao_detran (depende de veiculo)
CREATE TABLE integracao_detran (
    id_integracao SERIAL PRIMARY KEY,
    api VARCHAR(255),
    data_chamada TIMESTAMP,
    resultado TEXT,
    placa_veiculo VARCHAR(20) REFERENCES veiculo(placa)
);
