package luis_dominguez.eduardo_sanchez.hospital_bloom

import Modelo.Conexion
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class AddPaciente : Fragment() {
    private lateinit var CampoNombres: EditText
    private lateinit var CampoS4ngr3: EditText
    private lateinit var CampoTelefono: EditText
    private lateinit var CampoEnfermedades: EditText
    private lateinit var CampoHabitacion: EditText
    private lateinit var CampoMedicamentos: EditText
    private lateinit var CampoNacimiento: EditText
    private lateinit var CampoHora: EditText
    private lateinit var CampoCama: EditText
    private lateinit var btn_Add: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_add_pacientes, container, false)

        CampoNombres = root.findViewById(R.id.txt_Nombres)
        CampoS4ngr3 = root.findViewById(R.id.txt_TipoSangre)
        CampoTelefono = root.findViewById(R.id.txt_Telefono)
        CampoEnfermedades = root.findViewById(R.id.txt_Enfermedades)
        CampoHabitacion = root.findViewById(R.id.txt_Habitacion)
        CampoMedicamentos = root.findViewById(R.id.txt_Medicamentos)
        CampoNacimiento = root.findViewById(R.id.txt_Nacimiento)
        CampoHora = root.findViewById(R.id.txt_Hora_Medicacion)
        CampoCama = root.findViewById(R.id.txt_Cama)
        btn_Add = root.findViewById(R.id.btn_Agregar_Paciente)

        CampoNombres.filters = arrayOf(InputFilter.LengthFilter(15))
        CampoS4ngr3.filters = arrayOf(InputFilter.LengthFilter(8))
        CampoTelefono.filters = arrayOf(InputFilter.LengthFilter(11))
        CampoTelefono.inputType = InputType.TYPE_CLASS_NUMBER
        CampoTelefono.addTextChangedListener(TelefonoTextWatcher())
        CampoEnfermedades.filters = arrayOf(InputFilter.LengthFilter(30))
        CampoHabitacion.filters = arrayOf(InputFilter.LengthFilter(4))
        CampoMedicamentos.filters = arrayOf(InputFilter.LengthFilter(100))
        CampoNacimiento.filters = arrayOf(InputFilter.LengthFilter(30))

        CampoNombres.requestFocus()

        btn_Add.setOnClickListener {
            if (validarCampos()){
                CoroutineScope(Dispatchers.IO).launch {
                    val objConexion = Conexion().cadenaConexion()

                    val Add = objConexion?.prepareStatement("INSERT INTO Pacientes (UUID_paciente, nombres , tipo_sangre, telefono, enfermedad, fecha_nacimiento, hora_medicacion, Numero_habitacion, numero_cama) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ")!!

                    Add.setString(1, UUID.randomUUID().toString())
                    Add.setString(2, CampoNombres.text.toString())
                    Add.setString(3, CampoS4ngr3.text.toString())
                    Add.setString(4, CampoTelefono.text.toString())
                    Add.setString(5, CampoEnfermedades.text.toString())
                    Add.setString(6, CampoNacimiento.text.toString())
                    Add.setString(7, CampoHora.text.toString())
                    Add.setInt(8, CampoHabitacion.text.toString().toInt())
                    Add.setInt(9, CampoCama.text.toString().toInt())
                    Add.executeUpdate()
                }
            }
        }

        return root
    }

    private fun validarCampos(): Boolean{
        val Nombres = CampoNombres.text.toString()
        val Sangr3 = CampoS4ngr3.text.toString()
        val Telefono = CampoTelefono.text.toString()
        val Enfermedades = CampoEnfermedades.text.toString()
        val Habitacion = CampoHabitacion.text.toString()
        val Medicamentos = CampoMedicamentos.text.toString()
        val Nacimiento = CampoNacimiento.text.toString()
        val Cama = CampoCama.text.toString()
        val Hora = CampoHora.text.toString()

        var HayErrores = false

        if (Nombres.isEmpty()){
            CampoNombres.error = "Este campo es obligatorio"
        }else{
            CampoNombres.error = null
        }

        if (Sangr3.isEmpty()){
            CampoS4ngr3.error = "Este campo es obligatorio"
        }else{
            CampoS4ngr3.error = null
        }

        if (Telefono.isEmpty()){
            CampoTelefono.error = "Este campo es obligatorio"
        }else{
            CampoTelefono.error = null
        }

        if (Telefono.length < 8) {
            CampoTelefono.error = "El número telefonico debe de tener 8 digitos"
            HayErrores = true
        } else {
            CampoTelefono.error = null
        }

        if (Enfermedades.isEmpty()){
            CampoEnfermedades.error = "Este campo es obligatorio"
        }else{
            CampoEnfermedades.error = null
        }

        if (Habitacion.isEmpty()){
            CampoHabitacion.error = "Este campo es obligatorio"
        }else{
            CampoHabitacion.error = null
        }

        if (Medicamentos.isEmpty()){
            CampoMedicamentos.error = "Este campo es obligatorio"
        }else{
            CampoMedicamentos.error = null
        }

        if (Nacimiento.isEmpty()){
            CampoNacimiento.error = "Este campo es obligatorio"
        }else{
            CampoNacimiento.error = null
        }

        if (Hora.isEmpty()){
            CampoHora.error = "Este campo es obligatorio"
        }else{
            CampoHora.error = null
        }

        if (Cama.isEmpty()){
            CampoCama.error = "Este campo es obligatorio"
        }else{
            CampoCama.error = null
        }

        return !HayErrores
    }

    inner class TelefonoTextWatcher : TextWatcher {
        private var isUpdating = false
        private val hyphen = " - "

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            if (isUpdating) return
            isUpdating = true

            var str = s.toString().replace(hyphen, "").replace(" ", "")
            val formatted = StringBuilder()

            for (i in str.indices) {
                formatted.append(str[i])
                if ((i == 3 || i == 7) && i != str.length - 1) {
                    formatted.append(hyphen)
                }
            }

            CampoTelefono.setText(formatted.toString())
            CampoTelefono.setSelection(formatted.length)

            isUpdating = false
        }
    }
}