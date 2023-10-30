import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.farmingdale.alrajab.bcs421.databinding.ActivityDatabaseBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class DatabaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatabaseBinding
    private lateinit var dbHelper: NameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = NameRepository.getInstance(this)

        binding.readData.setOnClickListener { readData() }
        binding.writeData.setOnClickListener { writeData() }
        binding.updateData.setOnClickListener { updateData() }
        binding.readFromFileAndWriteToDB.setOnClickListener { readFromFileAndWriteToDB() }
    }

    private fun writeData() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()

        val user = User(firstName = firstName, lastName = lastName)
        dbHelper.addUser(user)
    }

    private fun readData() {
        val users = dbHelper.getAllUsers()
        users.forEach { user ->
            Log.d("Data", "ID: ${user.id}, First Name: ${user.firstName}, Last Name: ${user.lastName}")
        }
    }

    private fun updateData() {
        val userIdToUpdate = 1 // Replace with the ID of the user you want to update
        val newFirstName = "New First Name"
        val newLastName = "New Last Name"

        dbHelper.updateUser(User(id = userIdToUpdate, firstName = newFirstName, lastName = newLastName))
    }

    private fun readFromFileAndWriteToDB() {
        val fileName = "userdata.txt"
        val data = readDataFromFile(fileName)
        writeDataToDatabase(data)
    }

    private fun readDataFromFile(fileName: String): List<String> {
        val data = mutableListOf<String>()
        try {
            val file = File(filesDir, fileName)
            val reader = BufferedReader(FileReader(file))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                data.add(line!!)
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return data
    }

    private fun writeDataToDatabase(data: List<String>) {
        val userDao = dbHelper.getUserDao()
        data.forEach { line ->
            val parts = line.split(",")
            if (parts.size == 2) {
                val firstName = parts[0]
                val lastName = parts[1]
                val user = User(firstName = firstName, lastName = lastName)
                userDao.insert(user)
            }
        }
    }
}
