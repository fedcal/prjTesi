CREATE TABLE `cartella_clinica` (
  `id_cartella_clinica` int NOT NULL AUTO_INCREMENT,
  `gruppo_sanguigno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cartella_clinica`)
);

CREATE TABLE `cartelle` (
  `id_cartella` bigint NOT NULL AUTO_INCREMENT,
  `nome_cartella` varchar(500) DEFAULT NULL,
  `path_cartella` varchar(5000) DEFAULT NULL,
  `is_cartella_addestramento` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_cartella`),
  UNIQUE KEY `nome_cartella` (`nome_cartella`)
);

CREATE TABLE `contatto_riferimento` (
  `id_contatto` int NOT NULL AUTO_INCREMENT,
  `cognome` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `numero_cellulare` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_contatto`)
);

CREATE TABLE `infermiere_seq` (
  `next_val` bigint DEFAULT NULL
);

CREATE TABLE `malattia` (
  `id_malattia` int NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_malattia`)
);

CREATE TABLE `medicinale` (
  `id_medicinale` bigint NOT NULL,
  `descrizione` bigint DEFAULT NULL,
  `nome` bigint DEFAULT NULL,
  PRIMARY KEY (`id_medicinale`)
);

CREATE TABLE `medicinale_seq` (
  `next_val` bigint DEFAULT NULL
);

CREATE TABLE `medico` (
  `id_medico` bigint NOT NULL,
  `cognome_medico` bigint DEFAULT NULL,
  `nome_medico` bigint DEFAULT NULL,
  `turno_medico` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_medico`)
);

CREATE TABLE `operazione_medica` (
  `id_operazione_medica` int NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `tipologia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_operazione_medica`)
);

CREATE TABLE `referto_operazione` (
  `id_referto` int NOT NULL AUTO_INCREMENT,
  `data_referto` varchar(255) DEFAULT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `tipologia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_referto`)
);

CREATE TABLE `referto_visita_medica` (
  `id_referto` int NOT NULL AUTO_INCREMENT,
  `data_referto` varchar(255) DEFAULT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `tipologia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_referto`)
);

CREATE TABLE `visita_medica` (
  `id_visita_medica` int NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `tipologia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_visita_medica`)
);

CREATE TABLE `diagnosi` (
  `id_diagnosi` int NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(255) DEFAULT NULL,
  `tipo_diagnosi` varchar(255) DEFAULT NULL,
  `id_cartella_clinica` int DEFAULT NULL,
  PRIMARY KEY (`id_diagnosi`),
  FOREIGN KEY (`id_cartella_clinica`) REFERENCES `cartella_clinica` (`id_cartella_clinica`)
);

CREATE TABLE `documenti` (
  `id_documento` bigint NOT NULL AUTO_INCREMENT,
  `nome_documento` varchar(500) DEFAULT NULL,
  `estensione_documento` varchar(8) DEFAULT NULL,
  `path_documento` varchar(5000) DEFAULT NULL,
  `id_cartella` bigint DEFAULT NULL,
  PRIMARY KEY (`id_documento`),
  FOREIGN KEY (`id_cartella`) REFERENCES `cartelle` (`id_cartella`)
);

CREATE TABLE `malattia_cartella` (
  `id_relazione` int NOT NULL AUTO_INCREMENT,
  `id_cartella` int DEFAULT NULL,
  `id_malattia` int DEFAULT NULL,
  PRIMARY KEY (`id_relazione`),
  FOREIGN KEY (`id_malattia`) REFERENCES `malattia` (`id_malattia`),
  FOREIGN KEY (`id_cartella`) REFERENCES `cartella_clinica` (`id_cartella_clinica`)
);

CREATE TABLE `medicinale_cartella` (
  `id_relazione` int NOT NULL AUTO_INCREMENT,
  `id_cartella_clinica` int DEFAULT NULL,
  `id_medicinale` bigint DEFAULT NULL,
  PRIMARY KEY (`id_relazione`),
  FOREIGN KEY (`id_cartella_clinica`) REFERENCES `cartella_clinica` (`id_cartella_clinica`),
  FOREIGN KEY (`id_medicinale`) REFERENCES `medicinale` (`id_medicinale`)
);

CREATE TABLE `medicinale_prescrizione` (
  `id_relazione` int NOT NULL AUTO_INCREMENT,
  `id_cartella_clinica` int DEFAULT NULL,
  `id_medicinale` bigint DEFAULT NULL,
  `id_medico` bigint DEFAULT NULL,
  PRIMARY KEY (`id_relazione`),
  FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`),
  FOREIGN KEY (`id_cartella_clinica`) REFERENCES `cartella_clinica` (`id_cartella_clinica`),
  FOREIGN KEY (`id_medicinale`) REFERENCES `medicinale` (`id_medicinale`)
);

CREATE TABLE `operazione_cartella` (
  `id_relazione` int NOT NULL AUTO_INCREMENT,
  `id_cartella` int DEFAULT NULL,
  `id_medico` bigint DEFAULT NULL,
  `id_operazione` int DEFAULT NULL,
  PRIMARY KEY (`id_relazione`),
  FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`),
  FOREIGN KEY (`id_operazione`) REFERENCES `operazione_medica` (`id_operazione_medica`),
  FOREIGN KEY (`id_cartella`) REFERENCES `cartella_clinica` (`id_cartella_clinica`)
);

CREATE TABLE `operazione_prescrizione` (
  `id_relazione` int NOT NULL AUTO_INCREMENT,
  `id_cartella` int DEFAULT NULL,
  `id_medico` bigint DEFAULT NULL,
  `id_operazione` int DEFAULT NULL,
  PRIMARY KEY (`id_relazione`),
  FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`),
  FOREIGN KEY (`id_cartella`) REFERENCES `cartella_clinica` (`id_cartella_clinica`),
  FOREIGN KEY (`id_operazione`) REFERENCES `operazione_medica` (`id_operazione_medica`)
);

CREATE TABLE `rag_bot_pdf` (
  `id_rag_bot_pdf` bigint NOT NULL AUTO_INCREMENT,
  `nome_bot` varchar(255) DEFAULT NULL,
  `descrizione_bot` varchar(500) DEFAULT NULL,
  `url_spring` varchar(500) DEFAULT NULL,
  `url_python` varchar(500) DEFAULT NULL,
  `id_cartella_addestramento` bigint DEFAULT NULL,
  `id_cartella_caricamento_massivo` bigint DEFAULT NULL,
  PRIMARY KEY (`id_rag_bot_pdf`),
  FOREIGN KEY (`id_cartella_addestramento`) REFERENCES `cartelle` (`id_cartella`),
  FOREIGN KEY (`id_cartella_caricamento_massivo`) REFERENCES `cartelle` (`id_cartella`)
);

CREATE TABLE `reparto` (
  `id_reparto` bigint NOT NULL,
  `descrizione_reparto` bigint DEFAULT NULL,
  `nome_reparto` bigint DEFAULT NULL,
  `capo_reparto` bigint DEFAULT NULL,
  PRIMARY KEY (`id_reparto`),
  FOREIGN KEY (`capo_reparto`) REFERENCES `medico` (`id_medico`)
);

CREATE TABLE `visita_medica_cartella` (
  `id_relazione` int NOT NULL AUTO_INCREMENT,
  `id_cartella` int DEFAULT NULL,
  `id_referto` int DEFAULT NULL,
  `id_visita_medica` int DEFAULT NULL,
  PRIMARY KEY (`id_relazione`),
  FOREIGN KEY (`id_visita_medica`) REFERENCES `visita_medica` (`id_visita_medica`),
  FOREIGN KEY (`id_cartella`) REFERENCES `cartella_clinica` (`id_cartella_clinica`),
  FOREIGN KEY (`id_referto`) REFERENCES `referto_visita_medica` (`id_referto`)
);

CREATE TABLE `visita_prescrizione` (
  `id_relazione` int NOT NULL AUTO_INCREMENT,
  `id_cartella_clinica` int DEFAULT NULL,
  `id_medico` bigint DEFAULT NULL,
  `id_visita` int DEFAULT NULL,
  PRIMARY KEY (`id_relazione`),
  FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`),
  FOREIGN KEY (`id_cartella_clinica`) REFERENCES `cartella_clinica` (`id_cartella_clinica`),
  FOREIGN KEY (`id_visita`) REFERENCES `visita_medica` (`id_visita_medica`)
);

CREATE TABLE `visita_sottoministrazione_medico` (
  `id_relazione` int NOT NULL AUTO_INCREMENT,
  `id_cartella_clinica` int DEFAULT NULL,
  `id_medico` bigint DEFAULT NULL,
  `id_visita` int DEFAULT NULL,
  PRIMARY KEY (`id_relazione`),
  FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`),
  FOREIGN KEY (`id_visita`) REFERENCES `visita_medica` (`id_visita_medica`),
  FOREIGN KEY (`id_cartella_clinica`) REFERENCES `cartella_clinica` (`id_cartella_clinica`)
);

CREATE TABLE `infermiere` (
  `id_infermiere` bigint NOT NULL,
  `cognome_infermiere` varchar(100) DEFAULT NULL,
  `nome_infermiere` varchar(100) DEFAULT NULL,
  `turno_infermiere` varchar(100) DEFAULT NULL,
  `id_reparto` bigint NOT NULL,
  PRIMARY KEY (`id_infermiere`),
  FOREIGN KEY (`id_reparto`) REFERENCES `reparto` (`id_reparto`)
);

CREATE TABLE `medicinale_sottoministrazione` (
  `id_relazione` int NOT NULL AUTO_INCREMENT,
  `id_cartella_clinica` int DEFAULT NULL,
  `id_infermiere` bigint DEFAULT NULL,
  `id_medicinale` bigint DEFAULT NULL,
  PRIMARY KEY (`id_relazione`),
  FOREIGN KEY (`id_cartella_clinica`) REFERENCES `cartella_clinica` (`id_cartella_clinica`),
  FOREIGN KEY (`id_infermiere`) REFERENCES `infermiere` (`id_infermiere`),
  FOREIGN KEY (`id_medicinale`) REFERENCES `medicinale` (`id_medicinale`)
);

CREATE TABLE `paziente` (
  `id_paziente` bigint NOT NULL,
  `cognome` varchar(255) DEFAULT NULL,
  `data_nascita` varchar(255) DEFAULT NULL,
  `luogo_nascita` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `provincia_nascita` varchar(255) DEFAULT NULL,
  `id_cartella_clinica` int DEFAULT NULL,
  `contatto_riferimento` int DEFAULT NULL,
  `id_reparto` bigint DEFAULT NULL,
  PRIMARY KEY (`id_paziente`),
  FOREIGN KEY (`id_cartella_clinica`) REFERENCES `cartella_clinica` (`id_cartella_clinica`),
  FOREIGN KEY (`contatto_riferimento`) REFERENCES `contatto_riferimento` (`id_contatto`),
  FOREIGN KEY (`id_reparto`) REFERENCES `reparto` (`id_reparto`)
);

CREATE TABLE `visita_sottoministrazione_infermiere` (
  `id_relazione` int NOT NULL AUTO_INCREMENT,
  `id_cartella_clinica` int DEFAULT NULL,
  `id_infermiere` bigint DEFAULT NULL,
  `id_visita` int DEFAULT NULL,
  PRIMARY KEY (`id_relazione`),
  FOREIGN KEY (`id_visita`) REFERENCES `visita_medica` (`id_visita_medica`),
  FOREIGN KEY (`id_infermiere`) REFERENCES `infermiere` (`id_infermiere`),
  FOREIGN KEY (`id_cartella_clinica`) REFERENCES `cartella_clinica` (`id_cartella_clinica`)
);
