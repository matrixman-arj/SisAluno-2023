
CREATE TABLE IF NOT EXISTS ensino.ano
(
    codigo integer NOT NULL,
    ano character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ano_pkey PRIMARY KEY (codigo)
);


CREATE TABLE ensino.aluno_ano(
	codigo_aluno serial NOT NULL,
	codigo_ano serial NOT NULL,
	
	CONSTRAINT aluno_ano_pkey PRIMARY KEY (codigo_aluno, codigo_ano),
	
	CONSTRAINT codigo_aluno_fkey FOREIGN KEY (codigo_aluno)
    REFERENCES ensino.aluno (codigo),
    
    CONSTRAINT codigo_ano_fkey FOREIGN KEY (codigo_ano)
    REFERENCES ensino.ano (codigo)
);