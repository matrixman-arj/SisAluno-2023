CREATE TABLE ensino.matricula(
	codigo serial NOT NULL,
	numero_matricula character varying(100),
	codigo_aluno bigint NOT NULL,
	codigo_usuario bigint NOT NULL,
	periodo character varying(100),
	codigo_om bigint NOT NULL,
	uete character varying(100), 
	data_inicio_curso DATE,
	data_final_curso DATE,
	ano_letivo character varying(10),
	atitudinal numeric(10,2),
	atitudinal_lateral numeric(10,2),
	atitudinal_vertical numeric(10,2),
	tfm numeric(10,2),
	tfm2 numeric(10,2),
	tfm3 numeric(10,2),		
	situacao_no_curso character varying(100),	
	situacao_no_curso_descr character varying(100),
	
	CONSTRAINT matricula_pkey PRIMARY KEY (codigo)	
	
);

CREATE TABLE ensino.item_matricula(
	codigo serial NOT NULL,
	codigo_curso bigint NOT NULL,
	codigo_matricula bigint NOT NULL,	
	CONSTRAINT item_matricula_pkey PRIMARY KEY (codigo),
	FOREIGN KEY (codigo_curso)REFERENCES ensino.curso(codigo),
	FOREIGN KEY (codigo_matricula)REFERENCES ensino.matricula(codigo)
	
);