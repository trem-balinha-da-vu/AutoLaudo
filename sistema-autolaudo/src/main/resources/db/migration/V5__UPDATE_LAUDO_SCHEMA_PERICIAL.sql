-- 1. Tabela status do laudo
CREATE TABLE IF NOT EXISTS laudo_status (
    id_status SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO laudo_status (nome) VALUES
('PENDENTE'), ('EM_ANDAMENTO'), ('EM_ATRASO'), ('AGUARDANDO_AVALIACAO'), ('APROVADO'), ('REJEITADO')
ON CONFLICT DO NOTHING;

-- 2. Tabela de órgão requisitante
CREATE TABLE IF NOT EXISTS orgao_requisitante (
    id_orgao SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO orgao_requisitante (nome) VALUES
('MINISTÉRIO PÚBLICO'), ('POLÍCIA CIVIL'), ('POLÍCIA MILITAR'), ('PODER JUDICIÁRIO'), ('OUTROS')
ON CONFLICT DO NOTHING;

-- 3. Nova tabela laudo_veicular (pericial)
DROP TABLE IF EXISTS laudo_veicular CASCADE;
CREATE TABLE laudo_veicular (
    id_laudo SERIAL PRIMARY KEY,
    data_criacao TIMESTAMP NOT NULL DEFAULT now(),
    data_entrega TIMESTAMP NOT NULL,
    data_emissao TIMESTAMP,
    resumo_problema TEXT NOT NULL,
    detalhamento_analises TEXT,
    conclusao TEXT,
    observacoes TEXT,
    hash_documento VARCHAR(255),
    id_status INTEGER NOT NULL REFERENCES laudo_status(id_status),
    id_orgao INTEGER NOT NULL REFERENCES orgao_requisitante(id_orgao),
    id_selo INTEGER REFERENCES selo_digital(id_selo),
    id_formulario INTEGER REFERENCES formulario_laudo(id_formulario),
    placa_veiculo VARCHAR(20) NOT NULL REFERENCES veiculo(placa),
    id_perito INTEGER NOT NULL REFERENCES usuario(id)
);

-- 4. Tabela de relação laudo <-> norma (N-N)
DROP TABLE IF EXISTS laudo_norma CASCADE;
CREATE TABLE laudo_norma (
    id_laudo INTEGER REFERENCES laudo_veicular(id_laudo),
    id_norma INTEGER REFERENCES norma(id_norma),
    PRIMARY KEY (id_laudo, id_norma)
);

-- 5. Tabela de campos do formulário do laudo (chave composta: id_formulario + nome_campo)
DROP TABLE IF EXISTS form_laudo_campo CASCADE;
CREATE TABLE form_laudo_campo (
    id_formulario INTEGER NOT NULL,
    nome_campo VARCHAR(255) NOT NULL,
    valor TEXT,
    PRIMARY KEY (id_formulario, nome_campo),
    FOREIGN KEY (id_formulario) REFERENCES formulario_laudo(id_formulario)
);

-- 6. Tabela de histórico de alteração (ajustada p/ id do usuario correto)
DROP TABLE IF EXISTS historico_alteracao CASCADE;
CREATE TABLE historico_alteracao (
    id_hist SERIAL PRIMARY KEY,
    data_alteracao TIMESTAMP NOT NULL DEFAULT now(),
    descricao TEXT NOT NULL,
    id_usuario INTEGER REFERENCES usuario(id),
    id_laudo INTEGER REFERENCES laudo_veicular(id_laudo)
);

-- 7. Tabela de sincronizador
DROP TABLE IF EXISTS sincronizador CASCADE;
CREATE TABLE sincronizador (
    id_sync SERIAL PRIMARY KEY,
    data_sync TIMESTAMP,
    id_usuario INTEGER REFERENCES usuario(id),
    id_laudo INTEGER REFERENCES laudo_veicular(id_laudo)
);

-- 8. Tabela de template_laudo
CREATE TABLE IF NOT EXISTS template_laudo (
    id_template SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    versao VARCHAR(20)
);

-- 9. Tabela de formulário_laudo (vincula template, modo offline)
CREATE TABLE IF NOT EXISTS formulario_laudo (
    id_formulario SERIAL PRIMARY KEY,
    modo_offline BOOLEAN,
    id_template INTEGER REFERENCES template_laudo(id_template)
);

-- 10. Tabela de selo_digital
CREATE TABLE IF NOT EXISTS selo_digital (
    id_selo SERIAL PRIMARY KEY,
    data_emissao TIMESTAMP,
    autoridade_emissora VARCHAR(255),
    hash_conformidade VARCHAR(255)
);

-- 11. Tabela imagem (vincula laudo)
CREATE TABLE IF NOT EXISTS imagem (
    id_imagem SERIAL PRIMARY KEY,
    caminho VARCHAR(255),
    legenda VARCHAR(255),
    tipo VARCHAR(50),
    id_laudo INTEGER REFERENCES laudo_veicular(id_laudo)
);

-- 12. Tabela integracao_detran (vincula veiculo)
CREATE TABLE IF NOT EXISTS integracao_detran (
    id_integracao SERIAL PRIMARY KEY,
    api VARCHAR(255),
    data_chamada TIMESTAMP,
    resultado TEXT,
    placa_veiculo VARCHAR(20) REFERENCES veiculo(placa)
);
