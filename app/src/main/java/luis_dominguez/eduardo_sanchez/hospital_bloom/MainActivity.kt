package luis_dominguez.eduardo_sanchez.hospital_bloom

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import luis_dominguez.eduardo_sanchez.hospital_bloom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_Paciente, R.id.navigation_Add_Paciente, R.id.navigation_Detalles_Pacientes
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val nombres = intent.getStringExtra("nombres")
        val tipoSangre = intent.getStringExtra("tipo_sangre")
        val telefono = intent.getStringExtra("telefono")
        val enfermedad = intent.getStringExtra("enfermedad")
        val fechaNacimiento = intent.getStringExtra("fecha_nacimiento")
        val horaMedicacion = intent.getStringExtra("hora_medicacion")
        val numeroHabitacion = intent.getStringExtra("numero_habitacion")
        val numeroCama = intent.getStringExtra("numero_cama")
        val medicamentos = intent.getStringExtra("medicamentos")

        if (nombres != null && tipoSangre != null && telefono != null && enfermedad != null && fechaNacimiento != null && horaMedicacion != null && numeroHabitacion != null && numeroCama != null && medicamentos != null) {
            val fragment = Detalles_Pacientes().apply {
                arguments = Bundle().apply {
                    putString("nombres", nombres)
                    putString("tipo_sangre", tipoSangre)
                    putString("telefono", telefono)
                    putString("enfermedad", enfermedad)
                    putString("fecha_nacimiento", fechaNacimiento)
                    putString("hora_medicacion", horaMedicacion)
                    putString("numero_habitacion", numeroHabitacion)
                    putString("numero_cama", numeroCama)
                    putString("medicamentos", medicamentos)
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .commit()
        }
    }
}
