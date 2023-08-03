CREATE TABLE ensino.curso_om(
	codigo_curso serial NOT NULL,
	codigo_om serial NOT NULL,
	
	CONSTRAINT curso_om_pkey PRIMARY KEY (codigo_curso, codigo_om),
	
	CONSTRAINT codigo_curso_fkey FOREIGN KEY (codigo_curso)
    REFERENCES ensino.curso (codigo),
    
    CONSTRAINT codigo_om_fkey FOREIGN KEY (codigo_om)
    REFERENCES comum.om (codigo)
);