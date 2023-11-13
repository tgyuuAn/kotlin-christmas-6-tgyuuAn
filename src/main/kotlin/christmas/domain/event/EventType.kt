package christmas.domain.event

enum class EventType(val eventDescription: String) {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    WEEK_DAY("평일 할인"),
    WEEKEND("주말 할인"),
    STARRED_DAY("특별 할인"),
    FREEBIE("증정 이벤트")
}