CREATE TABLE Categoria (
    id int NOT NULL,
    nome varchar(100) UNIQUE NOT NULL,
    CONSTRAINT pk_categoria PRIMARY KEY (id)
);

CREATE TABLE Subcategoria (
    id int NOT NULL,
    nome varchar(100) NOT NULL,
    id_categoria INT NOT NULL,
    CONSTRAINT pk_subcategoria PRIMARY KEY (id),
    FOREIGN KEY(id_categoria) REFERENCES Categoria (id)
);

CREATE TABLE Lancamento (
    id int NOT NULL,
    id_subcategoria INT NOT NULL,
    valor NUMERIC(20,2) NOT NULL,
    data DATE NOT NULL DEFAULT CURRENT_DATE,
    comentario TEXT,
    CONSTRAINT pk_lancamento PRIMARY KEY (id),
    FOREIGN KEY(id_subcategoria) REFERENCES Subcategoria (id)
);

