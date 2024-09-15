public class ElonsToyCar {
    int battery;
    int distance;

    public ElonsToyCar() {
        this.battery = 100;
        this.distance = 0;
    }

    public static ElonsToyCar buy() {
        return new ElonsToyCar();
    }

    public String distanceDisplay() {
        return String.format("Driven %d meters", this.distance);
    }

    public String batteryDisplay() {
        return this.battery > 0 ? String.format("Battery at %d%%", this.battery) : "Battery empty";
    }

    public void drive() {
        if (this.battery > 0) {
            this.battery--;
            this.distance += 20;
        }
    }
}
