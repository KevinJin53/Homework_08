import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.id.layout.activity_user_profile)

        val firstNameEditText = findViewById<EditText>(R.id.firstNameEditText)
        val lastNameEditText = findViewById<EditText>(R.id.lastNameEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)

        // Load saved data from SharedPreferences and populate the EditText fields
        val sharedPref = getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        val savedFirstName = sharedPref.getString("firstName", "")
        val savedLastName = sharedPref.getString("lastName", "")

        firstNameEditText.setText(savedFirstName)
        lastNameEditText.setText(savedLastName)

        saveButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()

            // Save data to SharedPreferences
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putString("firstName", firstName)
            editor.putString("lastName", lastName)
            editor.apply() // or use editor.commit() for immediate saving
        }
    }
}
