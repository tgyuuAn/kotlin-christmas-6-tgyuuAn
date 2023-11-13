package christmas.domain

sealed class MenuCategory {
    sealed class Appetizer : MenuCategory() {
        data class MushroomSoup(val price: Int = 6000) : Appetizer()
        data class Tapas(val price: Int = 5500) : Appetizer()
        data class CaesarSalad(val price: Int = 8000) : Appetizer()
    }

    sealed class MainCourse : MenuCategory() {
        data class TboneSteak(val price: Int = 55000) : MainCourse()
        data class BarbecueRibs(val price: Int = 54000) : MainCourse()
        data class SeafoodPasta(val price: Int = 35000) : MainCourse()
        data class ChristmasPasta(val price: Int = 25000) : MainCourse()
    }

    sealed class Dessert : MenuCategory() {
        data class ChocolateCake(val price: Int = 15000) : Dessert()
        data class IceCream(val price: Int = 5000) : Dessert()
    }

    sealed class Drink : MenuCategory() {
        data class ZeroCola(val price: Int = 3000) : Drink()
        data class RedWine(val price: Int = 60000) : Drink()
        data class Champagne(val price: Int = 25000) : Drink()
    }
}