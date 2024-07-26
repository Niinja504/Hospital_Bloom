package luis_dominguez.eduardo_sanchez.hospital_bloom

import Modelo.Conexion
import Modelo.dataClassPacientes
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import RecyclerViewHelpers.Adaptador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Pacientes : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_pacientes, container, false)

        val rcvPaciente = root.findViewById<RecyclerView>(R.id.RCV_Pacientes)
        //Asignarle un Layout al RecyclerView
        rcvPaciente .layoutManager = LinearLayoutManager(requireContext())

        fun mostrarDatos(): List<dataClassPacientes> {
            //1- Creo un objeto de la clase conexion
            val objConexion = Conexion().cadenaConexion()

            //2- Creo un Statement
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM Pacientes")!!

            //Voy a guardar all lo que me traiga el Select

            val Pacientes = mutableListOf<dataClassPacientes>()

            while (resultSet.next()){
                val Nombre = resultSet.getString("nombres")
                val Sangr3 = resultSet.getString("tipo_sangre")
                val Telefono = resultSet.getString("telefono")
                val Enfermedades = resultSet.getString("enfermedad")
                val Nacimiento = resultSet.getString("fecha_nacimiento")
                val Hora = resultSet.getString("hora_medicacion")
                val Habitacion = resultSet.getInt("Numero_habitacion")
                val Cama = resultSet.getInt("numero_cama")
                val Medicamentos = resultSet.getString("Medicamentos")
                val uuid = resultSet.getString("UUID_paciente")
                val paciente = dataClassPacientes(uuid, Nombre, Sangr3, Telefono, Enfermedades, Nacimiento, Hora, Habitacion, Cama, Medicamentos)
                Pacientes.add(paciente)
            }
            return Pacientes
        }

        CoroutineScope(Dispatchers.IO).launch{
            //Creo una variable que ejecute la funcion de mostrar datos
            val PacienteDB = mostrarDatos()
            withContext(Dispatchers.Main){
                val miAdaptador = Adaptador(PacienteDB)
                rcvPaciente .adapter = miAdaptador
            }
        }

        return root
    }
}