package machine;

public enum Coffee {
    ESPRESSO(1, 250, 0, 16, 4),
    LATTE(2, 350,75, 20, 7),
    CAPPUCCINO(3, 200,100,12,6);

    public final int id;
    public final int water;
    public final int milk;
    public final int beans;
    public final int cost;

    Coffee(int id, int water, int milk, int beans, int cost) {

        this.id = id;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cost = cost;
    }
}
