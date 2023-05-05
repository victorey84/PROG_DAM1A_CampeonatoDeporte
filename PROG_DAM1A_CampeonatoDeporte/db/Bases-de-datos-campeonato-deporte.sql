CREATE TABLE equipo 
(
    codigo INTEGER NOT NULL,
    Nombre VARCHAR NOT NULL,
    año_fundacion INTEGER NOT NULL,
    lugar_sede VARCHAR NOT NULL,
    estadio VARCHAR NOT NULL,
    socios_aficionados INTEGER NOT NULL,
    PRIMARY KEY(codigo)
);

CREATE TABLE jugador
(
    codigo INTEGER NOT NULL PRIMARY KEY,
    nombre VARCHAR NOT NULL,
    fecha_nacimiento VARCHAR NOT NULL,
    nacionalidad VARCHAR NOT NULL,
    posicion VARCHAR NOT NULL
);

CREATE TABLE jugador_equipo
(
    codigo_equipo INTEGER NOT NULL,
    codigo_jugador INTEGER NOT NULL,
    año_entrada INTEGER NOT NULL,
    año_salida INTEGER,
    partidos_titular INTEGER,
    FOREIGN KEY (codigo_equipo) REFERENCES equipo(codigo),
    FOREIGN KEY (codigo_jugador) REFERENCES jugador(codigo),
    PRIMARY KEY(codigo_equipo, codigo_jugador)
);

CREATE TABLE partido
(
    codigo_equipo_local INTEGER NOT NULL,
    codigo_equipo_visitante INTEGER NOT NULL,
    año_temporada INTEGER NOT NULL,
    fecha VARCHAR NOT NULL,
    puntuacion_local INTEGER,
    puntuacion_visitante INTEGER,
    FOREIGN KEY (codigo_equipo_local) REFERENCES equipo(codigo),
    FOREIGN KEY (codigo_equipo_visitante) REFERENCES equipo(codigo),
    PRIMARY KEY(codigo_equipo_local, codigo_equipo_visitante, año_temporada)
);