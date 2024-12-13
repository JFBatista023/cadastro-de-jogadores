CREATE TABLE IF NOT EXISTS jogadores (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR2(255) NOT NULL,
    email VARCHAR2(255) NOT NULL,
    telefone VARCHAR2(20),
    codinome VARCHAR2(255) NOT NULL,
    grupo_codinome VARCHAR2(255) NOT NULL,
    CONSTRAINT uk_codinome_grupo UNIQUE (codinome, grupo_codinome)
);