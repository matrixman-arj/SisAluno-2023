CREATE TABLE comum.grupo(
    codigo serial NOT NULL,
    nome character varying(50) NOT NULL,      
    
    CONSTRAINT grupo_pkey PRIMARY KEY (codigo)   
);

CREATE TABLE comum.permissao(
    codigo serial NOT NULL,
    nome character varying(50) NOT NULL,
    filtrar character varying(50),
    buscar_com_grupo character varying(50),
    
    CONSTRAINT permissao_pkey PRIMARY KEY (codigo)  
);

CREATE TABLE comum.usuario_grupo(
	codigo_usuario serial NOT NULL,
	codigo_grupo serial NOT NULL,
	
	CONSTRAINT usuario_grupo_pkey PRIMARY KEY (codigo_usuario, codigo_grupo),
	
	CONSTRAINT codigo_usuario_fkey FOREIGN KEY (codigo_usuario)
    REFERENCES comum.usuario (codigo),
    
    CONSTRAINT codigo_grupo_fkey FOREIGN KEY (codigo_grupo)
    REFERENCES comum.grupo (codigo)
);

CREATE TABLE comum.grupo_permissao(
	codigo_grupo serial NOT NULL,
	codigo_permissao serial NOT NULL,
	
	CONSTRAINT permissao_grupo_pkey PRIMARY KEY (codigo_grupo, codigo_permissao),
	
    CONSTRAINT codigo_grupo_fkey FOREIGN KEY (codigo_grupo)
    REFERENCES comum.grupo (codigo),
	
	CONSTRAINT codigo_permissao_fkey FOREIGN KEY (codigo_permissao)
    REFERENCES comum.permissao (codigo)    

);
