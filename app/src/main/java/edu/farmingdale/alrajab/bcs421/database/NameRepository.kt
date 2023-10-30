import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class NameRepository private constructor(private val userDao: UserDao) {

    companion object {
        @Volatile
        private var instance: NameRepository? = null

        fun getInstance(context: Context): NameRepository {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "my-database"
                ).build()
                instance = NameRepository(database.userDao())
                instance!!
            }
        }
    }

    fun addUser(user: User) {
        userDao.insert(user)
    }

    fun updateUser(user: User) {
        userDao.update(user)
    }

    fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }
}
