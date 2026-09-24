import java.util.Objects;

public class Doctor {
    private String lastname;
    private String firstname;
    private double basicPay;

    public Doctor(String lastname, String firstname, double basicPay) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.basicPay = basicPay;
    }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public double getBasicPay() { return basicPay; }
    public void setBasicPay(double basicPay) { this.basicPay = basicPay; }

    public String tagName() {
        char initial = (firstname != null && !firstname.isEmpty()) ? firstname.charAt(0) : ' ';
        return "Dr. " + lastname + " " + initial + ".";
    }

    public double calculatePay() {
        return basicPay;
    }

    // Text representation of a Doctor object
    @Override
    public String toString() {
        return "Doctor [Last Name=" + lastname + ", First Name=" + firstname + ", Basic Pay=" + basicPay + "]";
    }

    // Checks equality based on lastname, firstname, and basicPay
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Doctor doctor = (Doctor) obj;
        return Double.compare(doctor.basicPay, basicPay) == 0 &&
               Objects.equals(lastname, doctor.lastname) &&
               Objects.equals(firstname, doctor.firstname);
    }
}
