CREATE TABLE Pacientes (
    UUID_paciente VARCHAR2(50) PRIMARY KEY,
    nombres VARCHAR2(100) NOT NULL,
    tipo_sangre VARCHAR2(7),
    telefono VARCHAR2(12),
    enfermedad VARCHAR2(255),
    fecha_nacimiento VARCHAR(10),
    hora_medicacion VARCHAR2(10),
    Numero_habitacion NUMBER,
    numero_cama NUMBER,
    Medicamentos VARCHAR2(255)
);

SELECT * FROM Pacientes;


DROP TABLE Pacientes;
