CREATE TABLE ensino.aluno_om(
	codigo_aluno serial NOT NULL,
	codigo_om serial NOT NULL,
	
	CONSTRAINT aluno_om_pkey PRIMARY KEY (codigo_aluno, codigo_om),
	
	CONSTRAINT codigo_aluno_fkey FOREIGN KEY (codigo_aluno)
    REFERENCES ensino.aluno (codigo),
    
    CONSTRAINT codigo_om_fkey FOREIGN KEY (codigo_om)
    REFERENCES comum.om (codigo)
);