import java.util.Objects;

public class Surgeon extends Doctor {
    private int numProcedures;
    private double procedureRate;

    public Surgeon(String lastname, String firstname, double basicPay, int numProcedures, double procedureRate) {
        super(lastname, firstname, basicPay);
        this.numProcedures = numProcedures;
        this.procedureRate = procedureRate;
    }

    public int getNumProcedures() { return numProcedures; }
    public void setNumProcedures(int numProcedures) { this.numProcedures = numProcedures; }

    public double getProcedureRate() { return procedureRate; }
    public void setProcedureRate(double procedureRate) { this.procedureRate = procedureRate; }

    @Override
    public String tagName() {
        return super.tagName() + " (Surgeon)";
    }

    @Override
    public double calculatePay() {
        return getBasicPay() + (numProcedures * procedureRate);
    }

    // Calls super.toString() to include parent variables
    @Override
    public String toString() {
        return super.toString() + " -> Surgeon [Procedures=" + numProcedures + ", Procedure Rate=" + procedureRate + "]";
    }

    // Verifies parent attributes match, then checks Surgeon fields
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false; // Checks class match and base variables
        Surgeon surgeon = (Surgeon) obj;
        return numProcedures == surgeon.numProcedures && 
               Double.compare(surgeon.procedureRate, procedureRate) == 0;
    }
}
