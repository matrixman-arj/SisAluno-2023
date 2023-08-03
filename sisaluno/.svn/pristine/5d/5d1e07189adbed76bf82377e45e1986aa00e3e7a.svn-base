CREATE TABLE comum.cidade (
    codigo serial NOT NULL,
    nome VARCHAR(50) NOT NULL,   
    codigo_estado BIGINT NOT NULL,
    codigo_regiao BIGINT NOT NULL,
    
    CONSTRAINT cidade_pkey PRIMARY KEY (codigo),
    
    CONSTRAINT codigo_estado_fkey FOREIGN KEY (codigo_estado)
    REFERENCES comum.estado (codigo), 
    
    CONSTRAINT codigo_regiao_fkey FOREIGN KEY (codigo_regiao)
    REFERENCES comum.regiao (codigo)
   
);


INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Manaus', 1, 1);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Itacoatiara', 1, 1);

INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Belém', 2, 1);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Santarém',2, 1);

INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('SÃO LUÍS', 3, 2);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('IMPERATRIZ', 3, 2);


INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Rio Branco', 4, 1);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Cruzeiro do Sul', 4, 5);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Salvador', 10, 1);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Porto Seguro', 10, 2);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Santana', 10, 2);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Goiânia', 19, 3);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Itumbiara', 19, 3);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Novo Brasil', 19, 3);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Belo Horizonte', 20, 4);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Uberlândia', 20, 4);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Montes Claros', 20, 4);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Florianópolis', 25, 5);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Criciúma', 25, 5);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Camboriú', 25, 5);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Lages', 25, 5);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('São Paulo', 22, 4);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Ribeirão Preto', 22, 4);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Campinas', 22, 4);
INSERT INTO comum.cidade (nome, codigo_estado, codigo_regiao) VALUES ('Santos', 22, 4);