import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pizzaplay.model.PizzaModel

open class CurrentLiveModel : ViewModel() {
    val pizzas: MutableLiveData<PizzaModel> by lazy {
        MutableLiveData<PizzaModel>()
    }
}