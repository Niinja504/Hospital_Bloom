package luis_dominguez.eduardo_sanchez.hospital_bloom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class Detalles_Pacientes : Fragment()  {
    companion object {
        fun newInstance(
            nombres: String,
            tipo_sangre: String,
            telefono: String,
            enfermedad: String,
            fecha_nacimiento: String,
            hora_medicacion: String,
            numero_habitacion: String,
            numero_cama: String,
            medicamentos: String
        ): Detalles_Pacientes {
            val fragment = Detalles_Pacientes()
            val args = Bundle()
            args.putString("nombres", nombres)
            args.putString("tipo_sangre", tipo_sangre)
            args.putString("telefono", telefono)
            args.putString("enfermedad", enfermedad)
            args.putString("fecha_nacimiento", fecha_nacimiento)
            args.putString("hora_medicacion", hora_medicacion)
            args.putString("numero_habitacion", numero_habitacion)
            args.putString("numero_cama", numero_cama)
            args.putString("medicamentos", medicamentos)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_detalles_pacientes, container, false)

        val Ic_Regresar = root.findViewById<ImageView>(R.id.Regresar)

        val nombresRecibido = arguments?.getString("nombres")
        val tipo_sangreRecibido = arguments?.getString("tipo_sangre")
        val telefonoRecibido = arguments?.getString("telefono")
        val enfermedadRecibido = arguments?.getString("enfermedad")
        val fecha_nacimientoRecibido = arguments?.getString("fecha_nacimiento")
        val hora_medicacionRecibido = arguments?.getString("hora_medicacion")
        val numero_habitacionRecibido = arguments?.getString("numero_habitacion")
        val numero_camaRecibido = arguments?.getString("numero_cama")
        val medicamentosRecibido = arguments?.getString("medicamentos")

        val LblNombres = root.findViewById<TextView>(R.id.lbl_Nombre)
        val LblSangre = root.findViewById<TextView>(R.id.lbl_Sangre)
        val LblTelefono = root.findViewById<TextView>(R.id.lbl_Telefono)
        val LblEnfermedades = root.findViewById<TextView>(R.id.lbl_Enfermedades)
        val LblNacimiento = root.findViewById<TextView>(R.id.lbl_Nacimiento)
        val LblHora_Medicacion = root.findViewById<TextView>(R.id.lbl_HoraMedicacion)
        val LblHabitacion = root.findViewById<TextView>(R.id.lbl_Habitacion)
        val LblCama = root.findViewById<TextView>(R.id.lbl_Cama)
        val LblMedicamentos = root.findViewById<TextView>(R.id.lbl_Cama)

        LblNombres.text = nombresRecibido
        LblSangre.text = tipo_sangreRecibido
        LblTelefono.text = telefonoRecibido
        LblEnfermedades.text = enfermedadRecibido
        LblNacimiento.text = fecha_nacimientoRecibido
        LblHora_Medicacion.text = hora_medicacionRecibido
        LblHabitacion.text = numero_habitacionRecibido.toString()
        LblCama.text = numero_camaRecibido.toString()
        LblMedicamentos.text = medicamentosRecibido

        Ic_Regresar.setOnClickListener{
            findNavController().navigate(R.id.navigation_Paciente)
        }

        return root
    }
}