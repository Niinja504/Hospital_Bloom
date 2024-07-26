package RecyclerViewHelpers

import Modelo.Conexion
import Modelo.dataClassPacientes
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import luis_dominguez.eduardo_sanchez.hospital_bloom.R

class Adaptador(var Datos: List<dataClassPacientes>): RecyclerView.Adapter<ViewHolder>() {

    fun ActualizarListaDespuesDeEditar(uuid: String, nuevoNombre: String){
        val Index = Datos.indexOfFirst { it.uuid == uuid }
        Datos[Index].nombres = nuevoNombre
        notifyItemChanged(Index)
    }

    fun eliminarDatos(nombreCancion: String, posicion: Int){
        //Eliminarlo de la lista
        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(posicion)

        //Eliminarlo de la base de datos
        GlobalScope.launch(Dispatchers.IO){
            //1- Creo un objeto de la clase conexion
            val objConexion = Conexion().cadenaConexion()

            //2- creo una variable que contenga
            //un PrepareStatement
            val deletePaciente = objConexion?.prepareStatement("DELETE Pacientes WHERE UUID_paciente = ?")!!
            deletePaciente.setString(1, nombreCancion)
            deletePaciente.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()
        }
        Datos = listaDatos.toList()
        notifyItemRemoved(posicion)
        notifyDataSetChanged()
    }

    fun actualizarDato(nombreCancion: String, uuid: String){
        GlobalScope.launch(Dispatchers.IO) {
            //1- Creo un obj de la clase conexion
            val ObjConexion = Conexion().cadenaConexion()

            //2-Creo una variable que contenga un PrepareStatement
            val UpdatePaciente = ObjConexion?.prepareStatement("UPDATE Pacientes SET  nombres = ? WHERE UUID_paciente = ?")!!
            UpdatePaciente.setString(1, nombreCancion)
            UpdatePaciente.setString(2, uuid )
            UpdatePaciente.executeUpdate()

            val commit = ObjConexion.prepareStatement("COMMIT")
            commit.executeUpdate()

            withContext(Dispatchers.Main){
                ActualizarListaDespuesDeEditar(uuid ,nombreCancion)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Conectar el RecyclerView con la Card
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Poder darle clic a la elemento de la card
        val item = Datos[position]
            holder.txtNombre.text = item.nombres

        //Todo: clic al icono de borrar
        holder.imgBorrar.setOnClickListener {
            //Creo la alerta para confirmar la eliminacion
            //1) Invoco el contexto

            val context = holder.itemView.context


            //2)Creo la alerta en blanco
            val builder = AlertDialog.Builder(context)

            builder.setTitle("Confirmación")
            builder.setMessage("¿Estás seguro que quiere borrar?")

            builder.setPositiveButton("Si"){dialog, wich ->
                eliminarDatos(item.nombres, position)
            }

            builder.setNegativeButton("No"){dialog, wich ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

        //TODO: ClIC AL ICONO DE EDITAR
        holder.imgEditar.setOnClickListener {
            val context = holder.itemView.context

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Actualizar")

            val cuadroTexto = EditText(context)
            cuadroTexto.setHint(item.nombres)
            builder.setView(cuadroTexto)

            //Botones
            builder.setPositiveButton("Actualizar"){
                    dialog, wich ->
                actualizarDato(cuadroTexto.text.toString(),item.uuid)
            }

            builder.setNegativeButton("Cancelar"){
                    dialog, wich ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}