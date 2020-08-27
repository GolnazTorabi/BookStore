import android.util.ArrayMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.book.store.stock.bookstore.pages.authentication.login.LoginViewModel
import com.book.store.stock.bookstore.di.component.ViewModelSubComponent
import com.book.store.stock.bookstore.pages.authentication.register.RegisterViewModel
import com.book.store.stock.bookstore.pages.authentication.splash.SplashScreenViewModel
import com.book.store.stock.bookstore.pages.dash_board.DashBoardViewModel
import com.book.store.stock.bookstore.pages.dash_board.filter.DashBoardFilterViewModel
import com.book.store.stock.bookstore.pages.order.OrderViewModel
import com.book.store.stock.bookstore.pages.setting.SettingViewModel
import com.book.store.stock.bookstore.pages.setting.new_data.book.SettingNewBookAdapter
import com.book.store.stock.bookstore.pages.setting.new_data.book.SettingNewBookViewModel
import com.book.store.stock.bookstore.pages.setting.new_data.book.add_new_book.SettingAddNewBookViewModel
import com.book.store.stock.bookstore.pages.setting.report.seller.SettingReportSellerViewModel
import com.book.store.stock.bookstore.pages.setting.report.stock_clerk.SettingReportStockClerkViewModel
import com.book.store.stock.bookstore.pages.setting.request.seller.SettingRequestSellerViewModel
import com.book.store.stock.bookstore.pages.setting.request.seller.new_request.SettingNewRequestViewModel
import com.book.store.stock.bookstore.pages.setting.search.SettingSearchViewModel
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
        creators[SplashScreenViewModel::class.java] = Callable { viewModelSubComponent!!.splashViewModel() }
        creators[RegisterViewModel::class.java] = Callable { viewModelSubComponent!!.registerViewModel() }
        creators[DashBoardViewModel::class.java] = Callable { viewModelSubComponent!!.dashboardViewModel() }
        creators[DashBoardFilterViewModel::class.java] = Callable { viewModelSubComponent!!.dashboardFilterViewModel() }
        creators[OrderViewModel::class.java] = Callable { viewModelSubComponent!!.orderViewModel() }
        creators[SettingViewModel::class.java] = Callable { viewModelSubComponent!!.settingViewModel() }
        creators[SettingNewBookViewModel::class.java] = Callable { viewModelSubComponent!!.newBookViewModel() }
        creators[SettingAddNewBookViewModel::class.java] = Callable { viewModelSubComponent!!.addNewBookViewModel() }
        creators[SettingReportSellerViewModel::class.java] = Callable { viewModelSubComponent!!.reportSellerViewModel() }
        creators[SettingReportStockClerkViewModel::class.java] = Callable { viewModelSubComponent!!.reportStockClerkViewModel() }
        creators[SettingRequestSellerViewModel::class.java] = Callable { viewModelSubComponent!!.requestSellerViewModel() }
        creators[SettingNewRequestViewModel::class.java] = Callable { viewModelSubComponent!!.newRequestViewModel() }
        creators[SettingSearchViewModel::class.java] = Callable { viewModelSubComponent!!.searchViewModel() }
    }
}
