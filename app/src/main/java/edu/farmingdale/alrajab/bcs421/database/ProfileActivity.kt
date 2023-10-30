import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.farmingdale.alrajab.bcs421.databinding.ProfileActivityBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ProfileActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        // Save data to SharedPreferences
        binding.btnSave.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()

            editor.putString("first_name", firstName)
            editor.putString("last_name", lastName)
            editor.apply()
        }

        // Update the data in SharedPreferences
        binding.btnUpdate.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()

            editor.putString("first_name", firstName)
            editor.putString("last_name", lastName)
            editor.apply()
        }

        // Read and display data from SharedPreferences
        val savedFirstName = sharedPreferences.getString("first_name", "")
        val savedLastName = sharedPreferences.getString("last_name", "")
        binding.tvProfileData.text = "First Name: $savedFirstName\nLast Name: $savedLastName"
    }
}
