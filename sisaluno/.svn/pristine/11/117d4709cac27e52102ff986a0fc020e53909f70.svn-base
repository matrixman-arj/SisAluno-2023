CREATE TABLE comum.om (
    codigo serial NOT NULL,
    descricao character varying(255),
    sigla character varying(255),   
    ordem integer,
    codigo_estado BIGINT,
    codigo_regiao BIGINT,
    codigo_rm BIGINT,
    
    CONSTRAINT om_pkey PRIMARY KEY (codigo),
            
    CONSTRAINT codigo_estado_fkey FOREIGN KEY (codigo_estado)
    REFERENCES comum.estado (codigo),
    
     CONSTRAINT codigo_regiao_fkey FOREIGN KEY (codigo_regiao)
    REFERENCES comum.regiao (codigo),
    
    CONSTRAINT codigo_rm_fkey FOREIGN KEY (codigo_rm)
    REFERENCES comum.regiao_militar (codigo)
);

CREATE TABLE ensino.npor (
    codigo serial NOT NULL,
    descricao character varying(255),
    sigla character varying(255),   
    ordem integer,
    codigo_estado BIGINT,
    codigo_rm BIGINT,
    
    CONSTRAINT npor_pkey PRIMARY KEY (codigo),   
    
    CONSTRAINT codigo_estado_fkey FOREIGN KEY (codigo_estado)
    REFERENCES comum.estado (codigo),
    
    CONSTRAINT codigo_rm_fkey FOREIGN KEY (codigo_rm)
    REFERENCES comum.regiao_militar (codigo)
);

CREATE TABLE ensino.uete (
    codigo serial NOT NULL,
    descricao character varying(255),
    sigla character varying(255),   
    ordem integer,
    codigo_estado BIGINT,
    codigo_rm BIGINT,
    
    CONSTRAINT uete_pkey PRIMARY KEY (codigo),   
    
    CONSTRAINT codigo_estado_fkey FOREIGN KEY (codigo_estado)
    REFERENCES comum.estado (codigo),
    
    CONSTRAINT codigo_rm_fkey FOREIGN KEY (codigo_rm)
    REFERENCES comum.regiao_militar (codigo)
);




INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('AMAN', 'Academia Militar das Agulhas Negras', 1,  1, 21);
INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('EsPCEx', 'Escola Preparatória de Cadetes do Exército', 2, 2, 22);
INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('CPOR/RJ', 'Centros de Preparação de Oficiais da Reserva – Rio de Janeiro', 1, 3, 21);
INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('CPOR/SP', 'Centros de Preparação de Oficiais da Reserva – São Paulo', 2, 4, 22);
INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('CPOR/PA', 'Centros de Preparação de Oficiais da Reserva – Porto Alegre', 3, 5, 26);
INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('CPOR/BH', 'Centros de Preparação de Oficiais da Reserva – Belo Horizonte', 4, 6, 20);
INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('CPOR/R', 'Centros de Preparação de Oficiais da Reserva – Recife', 7, 7, 9);
INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('ESFCEx', 'Escola de Saúde e Formação Complementar do Exército', 6, 8, 10);

INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('ESA', 'Escola de Sargento das Armas', 4, 9, 20);
INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('EsSLOG', 'Escola de Sargentos de Logística', 1, 10, 21);
INSERT INTO comum.om (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('CIAvEx', 'Centro de Instrução de Aviação do Exército', 2, 11, 22);





INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('38º BI', '38º Batalhão de infantaria', 1, 1, 23);

INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('28º BIL', '28º Batalhão de infantaria Leve', 2, 2, 22);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('2º BIL', '2º Batalhão de infantaria Leve', 2, 3, 22);

INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('7º BIB', '7º Batalhão de infantaria Blindada', 3, 4, 26);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('1º BCom', '1º Batalhão de Comunicações', 3, 5, 26);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('19º RcMec', '19º Regimento de Cavalaria Mecanizado', 3, 6, 26);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('3º GAAAe', '3º Grupo de Artilharia Antiaérea', 3, 7, 26);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('3º GAC/Ap', '3º Grupo de Artilharia de Campanha Autopropulsado', 3, 8, 26);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('3º R C Mec', '3º Regimento de Cavalaria Mecanizado', 3, 9, 26);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('9º BIMtz', '9° Batalhão de Infantaria Motorizado', 3, 10, 25);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('Pq R Mnt/3', 'Parque Regional de Manutenção da 3ª Região Militar', 3, 11, 26);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('12º B E Cmb Bld', '12º Batalhão de Engenharia de Combate Blindado', 3, 12, 26);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('9° B Log', '9º Batalhão Logístico', 3, 13, 26);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('12º BE Cmb Bld', '12º Batalhão de Engenharia de Combate Blindado', 3, 14, 26);

INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('4º GAAAe', '4º Grupo de Artilharia Antiaérea', 4, 15, 20);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('4º GAC', '4º Grupo de Artilharia de Campanha', 4, 16, 20);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('4º BE Cmb ', '4º Batalhão de Engenharia de Combate', 4, 17, 20);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('2° B Fv', '2º Batalhão Ferroviário', 4, 18, 20);


INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('13º BIB', '13º Batalhão de infantaria Blindada', 5, 19, 24);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('20º BIB', '20º Batalhão de infantaria Blindada', 5, 20, 24);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('23º BI', '23º Batalhão de infantaria', 5, 21, 25);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('28º GAC', '28º Grupo de Artilharia de Campanha', 5, 22, 25);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('33º BI Mec', '33º Batalhão de infantaria Mecanizada', 5, 23, 24);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('5º BE Cmb Bld', '5º Batalhão de Engenharia de Combate Blindado', 5, 24, 25);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('5º B Log', '5º Batalhão Logístico', 5, 25, 24);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('5º GAC AP', '5º Grupo de Artilharia de Campanha Autopropulsado', 5, 26, 24);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('5º RCC', '5º Regimento de Carros de Combate', 5, 27, 24);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('62º BI', '62º Batalhão de Infantaria', 5, 28, 25);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('63º BI', '63º Batalhão de Infantaria', 5, 29, 25);

INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('19º BC', '19º Batalhão de Caçadores ', 6, 30, 10);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('28º BC', '28º Batalhão de Caçadores ', 6, 31, 14);

INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('15º BIMtz', '15º Batalhão de Infantaria Motorizado', 7, 32, 11);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('16º BIMtz', '16º Batalhão de Infantaria Motorizado', 7, 33, 8);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('16º RCMec', '16º Regimento de Cavalaria Mecanizado', 7, 34, 11);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('59º BIMtz', '59º Batalhão de Infantaria Motorizado', 7, 35, 12);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('72º BIMtz', '72º Batalhão de Infantaria Motorizado', 7, 36, 9);

INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('2º BIS', '2º Batalhão de Infantaria de Selva', 8, 37, 2);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('24º BIL', '24º Batalhão de Infantaria Leve', 8, 38, 3);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('24º BIS', '24º Batalhão de Infantaria de Selva', 8, 39, 3);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('52º BIS', '52º Batalhão de Infantaria de Selva', 8, 40, 2);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('34º BIS', '34º Batalhão de Infantaria de Selva', 8, 41, 6);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('23º BLog Sl', '23º Batalhão Logístico de Selva', 8, 42, 2);

INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('18º GAC', '18º Grupo de Artilharia de Campanha', 9, 43, 17);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('20º RCB', '20º Regimento de Cavalaria Blindado', 9, 44, 15);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('44º BIMtz', '44º Batalhão de Infantaria Motorizado', 9, 45, 17);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('9º BE Cmb ', '9º Batalhão de Engenharia de Combate', 9, 46, 15);

INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('23º BC', '23º Batalhão de Caçadores', 10, 47, 6);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('25º BC', '25º Batalhão de Caçadores', 10, 48, 12);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('2º BECnst', '2º Batalhão de Engenharia de Construção', 10, 49, 12);

INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('32º GAC', 'Grupo de Artilharia de Campanha', 11, 50, 16);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('11º BECnst', '11º Batalhão de Engenharia de Construção', 11, 51, 18);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('BGP', 'Batalhão da Guarda Presidencial', 11, 52, 16);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('36º BIMtz', '36º Batalhão de Infantaria Motorizado', 11, 53, 18);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('Cia C2', 'Companhia de Comando e Controle', 11, 54, 16);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('22º BI', '22º Batalhão de Infantaria', 11, 55, 5);

INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('1º BIS', '1º Batalhão de Infantaria de Selva', 12, 56, 1);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('12º B Sup', '12º Batalhão de Suprimento', 12, 57, 1);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('4º BIS', '4º Batalhão de Infantaria de Selva', 12, 58, 4);
INSERT INTO ensino.npor (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('10° GAC Sl', '10º Grupo de Artilharia de Campanha de Selva', 12, 59, 15);


INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('20º RCB',   '20º Regimento de Cavalaria Blindado',    3, 1, 26);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('12º GAC',   '12º Grupo de Artilharia e Campanha',     2, 2, 22);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('1º GAAAE',  '1º Grupo de Artilharia Anti Aérea',      1, 3, 21);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('41º BIMTZ', '41º Batalhão de Infantaria Motorizada', 11, 4, 19);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('14º GAC',   '14º Grupo de Artilharia e Campanha',     4, 5, 20);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('23º BC',    '23º Batalhão de Cavalaria',              10, 6, 7);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('6º RBC',    '6º Regimento de Cavalaria Blindado',     3, 7, 26);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('23º BI',    '23º Batalhão de Infantaria',             5, 8, 25);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('13º RCMec', '13º Regimento de Cavalaria Mecanizado',  2, 9, 22);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('10º BIL',   '10º Batalhão de Infantaria Leve',       4, 10, 20);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('14º GAC L', '4º Grupo de Artilharia e Campanha Leve', 4, 11, 20);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('4º BE Cmb', '4º Batalhão de Engenharia de Combate',  4, 12, 20);
INSERT INTO ensino.uete (sigla, descricao, codigo_rm, ordem, codigo_estado) VALUES ('16º BIMtz', '16º Batalhão de Infantaria Motorizado', 7, 13, 8);

