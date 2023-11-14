package christmas.domain.menu

enum class MenuItem(val displayName: String, val menuType: MenuType, val price: Int) {
    MUSHROOM_SOUP("양송이수프", MenuType.APPETIZER, 6000),
    TAPAS("타파스", MenuType.APPETIZER, 5500),
    CAESAR_SALAD("시저샐러드", MenuType.APPETIZER, 8000),

    T_BONE_STEAK("티본스테이크", MenuType.MAIN, 55000),
    BARBECUE_RIB("바비큐립", MenuType.MAIN, 54000),
    SEAFOOD_PASTA("해산물파스타", MenuType.MAIN, 35000),
    CHRISTMAS_PASTA("크리스마스파스타", MenuType.MAIN, 25000),

    CHOCOLATE_CAKE("초코케이크", MenuType.DESSERT, 15000),
    ICE_CREAM("아이스크림", MenuType.DESSERT, 5000),

    ZERO_COLA("제로콜라", MenuType.BEVERAGE, 3000),
    RED_WINE("레드와인", MenuType.BEVERAGE, 60000),
    CHAMPAGNE("샴페인", MenuType.BEVERAGE, 25000)

    companion object {
        fun convertStringToMenuItemOrNull(input: String): MenuItem? {
            MenuItem.values().forEach {
                if (it.displayName == input) {
                    return it
                }
            }
            return null
        }
    }
}