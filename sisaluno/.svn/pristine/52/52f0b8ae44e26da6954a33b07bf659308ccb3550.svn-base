CREATE TABLE comum.usuario (
	codigo serial NOT NULL,
	identidade character varying(255),
	nome character varying(255),
	nome_guerra character varying(255),
	senha character varying(255),	
	posto_graduacao character varying(255),
	posto_descricao character varying(255),
	om_codigo bigint,
	antiguidade integer,
	ativo boolean DEFAULT true,
	email character varying(255),	
	foto character varying(100),
	content_type character varying(100),
	
	CONSTRAINT usuario_pkey PRIMARY KEY (codigo),
	
	CONSTRAINT om_codigo_fkey FOREIGN KEY (om_codigo)
    REFERENCES comum.om (codigo),	
	UNIQUE (identidade)
	
);