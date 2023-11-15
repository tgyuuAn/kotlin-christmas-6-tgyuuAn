package christmas.domain.menu

enum class MenuItem(val displayName: String, val menuCategory: MenuCategory, val price: Int) {
    MUSHROOM_SOUP("양송이수프", MenuCategory.APPETIZER, 6000),
    TAPAS("타파스", MenuCategory.APPETIZER, 5500),
    CAESAR_SALAD("시저샐러드", MenuCategory.APPETIZER, 8000),

    T_BONE_STEAK("티본스테이크", MenuCategory.MAIN, 55000),
    BARBECUE_RIB("바비큐립", MenuCategory.MAIN, 54000),
    SEAFOOD_PASTA("해산물파스타", MenuCategory.MAIN, 35000),
    CHRISTMAS_PASTA("크리스마스파스타", MenuCategory.MAIN, 25000),

    CHOCOLATE_CAKE("초코케이크", MenuCategory.DESSERT, 15000),
    ICE_CREAM("아이스크림", MenuCategory.DESSERT, 5000),

    ZERO_COLA("제로콜라", MenuCategory.BEVERAGE, 3000),
    RED_WINE("레드와인", MenuCategory.BEVERAGE, 60000),
    CHAMPAGNE("샴페인", MenuCategory.BEVERAGE, 25000);

    companion object {
        fun convertInputToMenuItemOrNull(input: String): MenuItem? {
            MenuItem.values().forEach { menuItem ->
                if (menuItem.displayName == input) {
                    return menuItem
                }
            }
            return null
        }
    }
}