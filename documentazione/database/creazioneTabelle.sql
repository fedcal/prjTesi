CREATE TABLE cartelle(
    id_cartella BIGINT  AUTO_INCREMENT NOT NULL primary key,
    nome_cartella varchar(500) unique,
    path_cartella varchar(5000)
);

CREATE TABLE documenti(
    id_documento BIGINT primary key AUTO_INCREMENT NOT NULL,
    nome_documento varchar(500),
    estensione_documento varchar(10),
    path_documento varchar(5000),
    id_cartella BIGINT,
    FOREIGN KEY (id_cartella) REFERENCES cartelle(id_cartella)
);

CREATE TABLE rag_bot_pdf(
    id_rag_bot_pdf BIGINT primary key AUTO_INCREMENT NOT NULL,
    nome_bot varchar(500) unique,
    descrizione_bot varchar(500),
    url_spring varchar(500) unique,
    url_python varchar(500) unique,
    id_cartella_addestramento BIGINT,
    id_cartella_caricamento_massivo BIGINT,
    FOREIGN KEY (id_cartella_addestramento) REFERENCES cartelle(id_cartella),
    FOREIGN KEY (id_cartella_caricamento_massivo) REFERENCES cartelle(id_cartella)
);