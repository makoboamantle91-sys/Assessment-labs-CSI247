import java.util.Objects;

public class GP extends Doctor {
    private double rate; 

    public GP(String lastname, String firstname, double basicPay, double rate) {
        super(lastname, firstname, basicPay);
        this.rate = rate;
    }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    @Override
    public String tagName() {
        return super.tagName() + " (GP)";
    }

    @Override
    public double calculatePay() {
        double allowance = getBasicPay() * rate;
        return getBasicPay() + allowance;
    }

    // Calls super.toString() to include parent variables
    @Override
    public String toString() {
        return super.toString() + " -> GP [Allowance Rate=" + rate + "]";
    }

    // Verifies parent attributes match, then checks GP fields
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false; // Checks class match and base variables
        GP gp = (GP) obj;
        return Double.compare(gp.rate, rate) == 0;
    }
}
