import android.util.ArrayMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.book.store.stock.bookstore.base_pages.authentication.login.LoginViewModel
import com.book.store.stock.bookstore.di.component.ViewModelSubComponent
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(viewModelSubComponent: ViewModelSubComponent?) :
    ViewModelProvider.Factory {
    private val creators: ArrayMap<Class<*>, Callable<out ViewModel>?> = ArrayMap()
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key!!)) {
                    creator = value
                    break
                }
            }
        }
        requireNotNull(creator) { "Unknown model class $modelClass" }
        return try {
            creator.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    init {
        creators[LoginViewModel::class.java] = Callable { viewModelSubComponent!!.loginViewModel() }
    }
}