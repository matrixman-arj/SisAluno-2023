CREATE TABLE ensino.curso (	
	codigo serial NOT NULL,
	sku character varying(50),
	modalidade character varying(50),
	descricao character varying(50),
	universo character varying(15),
	universo_descr character varying(50),
	linha character varying(100),
	vinculo character varying(100),
	tipo_vinculo character varying(100),
	foto character varying(100),
	content_type character varying(100),
			
	CONSTRAINT curso_pkey PRIMARY KEY (codigo)
);
	