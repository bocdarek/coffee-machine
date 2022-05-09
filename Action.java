package machine;

public enum Action {
    SELL_COFFEE("buy"),
    FILL_MACHINE("fill"),
    PAY_MONEY_OUT("take"),
    INFORM_ABOUT_RESOURCES("remaining"),
    SHUTDOWN("exit"),
    ASK_FOR_ACTION("ask for action");

    public final String callout;

    Action(String callout) {
        this.callout = callout;
    }
}
