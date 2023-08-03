CREATE TABLE ensino.situacao_no_curso(
	codigo serial NOT NULL,	
			
	nome character varying(100),	
	descricao character varying(100),
	codigo_aluno bigint,
	
	CONSTRAINT situacao_no_curso_pkey PRIMARY KEY (codigo),
	
	CONSTRAINT codigo_aluno_fkey FOREIGN KEY (codigo_aluno)
    REFERENCES ensino.aluno (codigo)
	
);