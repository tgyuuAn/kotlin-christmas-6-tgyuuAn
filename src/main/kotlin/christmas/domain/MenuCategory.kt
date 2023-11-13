package christmas.domain

sealed class MenuCategory {
    sealed class Appetizer : MenuCategory() {
        object MushroomSoup : Appetizer()
        object Tapas : Appetizer()
        object CaesarSalad : Appetizer()
    }

    sealed class MainCourse : MenuCategory() {
        object TboneSteak : MainCourse()
        object BarbecueRibs : MainCourse()
        object SeafoodPasta : MainCourse()
        object ChristmasPasta : MainCourse()
    }

    sealed class Dessert : MenuCategory() {
        object ChocolateCake : Dessert()
        object IceCream : Dessert()
    }

    sealed class Drink : MenuCategory() {
        object ZeroCola : Drink()
        object RedWine : Drink()
        object Champagne : Drink()
    }
}