package Modelo

data class dataClassPacientes(
    var uuid: String,
    var nombres: String,
    var tipo_sangre: String,
    var telefono: String,
    var enfermedad: String,
    var fecha_nacimiento: String,
    var hora_medicacion: String,
    var Numero_habitacion: Int,
    var numero_cama: Int,
    var Medicamentos: String
)
