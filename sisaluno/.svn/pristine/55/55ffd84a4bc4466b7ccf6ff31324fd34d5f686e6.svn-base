CREATE schema comum;
CREATE TABLE comum.estado (
    codigo serial NOT NULL,
    nome VARCHAR(50) NOT NULL,
    sigla VARCHAR(25) NOT NULL,
    codigo_regiao BIGINT NOT NULL,
    
    CONSTRAINT estado_pkey PRIMARY KEY (codigo),
    
    CONSTRAINT codigo_regiao_fkey FOREIGN KEY (codigo_regiao)
    REFERENCES comum.regiao (codigo)    
      
);

CREATE TABLE comum.regiao (
    codigo serial NOT NULL,
    nome VARCHAR(50) NOT NULL,   
    codigo_estado BIGINT NOT NULL,
    CONSTRAINT regiao_pkey PRIMARY KEY (codigo)
    
     CONSTRAINT codigo_estado_fkey FOREIGN KEY (codigo_estado)
    REFERENCES comum.estado (codigo)
           
);

CREATE TABLE comum.regiao_militar (
    codigo serial NOT NULL,
    nome VARCHAR(50) NOT NULL, 
    
    CONSTRAINT regiao_militar_pkey PRIMARY KEY (codigo)
);


INSERT INTO comum.regiao_militar (codigo, nome) VALUES (1,'1ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (2,'2ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (3,'3ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (4,'4ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (5,'5ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (6,'6ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (7,'7ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (8,'8ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (9,'9ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (10,'10ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (11,'11ª-RM');
INSERT INTO comum.regiao_militar (codigo, nome) VALUES (12,'12ª-RM');

INSERT INTO comum.regiao (codigo, nome) VALUES (1,'Norte');
INSERT INTO comum.regiao (codigo, nome) VALUES (2,'Nordeste');
INSERT INTO comum.regiao (codigo, nome) VALUES (3,'Centro-Oeste');
INSERT INTO comum.regiao (codigo, nome) VALUES (4,'Sudeste');
INSERT INTO comum.regiao (codigo, nome) VALUES (5,'Sul');

INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (1,'Amazonas', 'AM', 1);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (2,'Pará', 'PA' , 1);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (3,'Maranhão', 'MA' , 1);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (4,'Acre', 'AC' , 1);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (5,'Tocantins', 'TO' , 1);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (6,'Amapá', 'AP' , 1);

INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (7,'Ceará', 'CE' , 2);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (8,'Rio Grande do Norte', 'RN' , 2);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (9,'Pernambuco', 'PE' , 2);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (10,'Bahia', 'BA' , 2);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (11,'Paraiba', 'PB' , 2);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (12,'Alagoas', 'AL' , 2);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (13,'Piaui', 'PI' , 2);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (14,'Sergipe', 'SE' , 2);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (15,'Roraima', 'RR' , 2);

INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (16,'Mato Grosso do Sul', 'MS' , 3);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (17,'Distrito Federal', 'DF' , 3);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (18,'Mato Grosso', 'MT' , 3);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (19,'Goiás', 'GO' , 3);

INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (20,'Minas Gerais', 'MG' , 4);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (21,'Rio de Janeiro' , 'RJ', 4);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (22,'São Paulo', 'SP' , 4);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (23,'Espirito Santo', 'ES' , 4);

INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (24,'Paraná', 'PR' , 5);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (25,'Santa Catarina', 'SC' , 5);
INSERT INTO comum.estado (codigo, nome, sigla, codigo_regiao) VALUES (26,'Rio Grande do Sul', 'RS' , 5);