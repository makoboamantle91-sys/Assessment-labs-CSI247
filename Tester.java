import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        String inputFileName = "data.txt";
        
        List<GP> gpList = new ArrayList<>();
        List<Surgeon> surgeonList = new ArrayList<>();
        List<Doctor> regularDoctorList = new ArrayList<>();

        // 1. Read and parse data.txt
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                // Split by semicolon as defined: Lastname;Firstname(s);pay;rate;number of procedures
                String[] tokens = line.split(";");
                
                // Parse required basic fields
                String lastname = tokens[0].trim();
                String firstname = tokens[1].trim();
                double pay = Double.parseDouble(tokens[2].trim());

                // Determine doctor type based on optional tokens
                if (tokens.length > 4 && !tokens[4].trim().isEmpty()) {
                    // If 'number of procedures' is explicitly present, it's a Surgeon
                    double rate = Double.parseDouble(tokens[3].trim());
                    int procedures = Integer.parseInt(tokens[4].trim());
                    surgeonList.add(new Surgeon(lastname, firstname, pay, procedures, rate));
                } 
                else if (tokens.length > 3 && !tokens[3].trim().isEmpty()) {
                    // If only 'rate' is present, it's a GP
                    double rate = Double.parseDouble(tokens[3].trim());
                    gpList.add(new GP(lastname, firstname, pay, rate));
                } 
                else {
                    // No optional fields means it's a regular baseline Doctor
                    regularDoctorList.add(new Doctor(lastname, firstname, pay));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.err.println("Data formatting error inside file: " + e.getMessage());
            return;
        }

        // 2. Export outputs to respective files
        writeDoctorsToFile("gps.txt", gpList);
        writeDoctorsToFile("surgeons.txt", surgeonList);
        writeDoctorsToFile("doctors.txt", regularDoctorList);

        System.out.println("Processing complete. Files 'gps.txt', 'surgeons.txt', and 'doctors.txt' have been generated.");
    }

    /**
     * Helper method to write doctor list records out to files
     */
    private static void writeDoctorsToFile(String fileName, List<? extends Doctor> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Doctor doc : list) {
                // Format: Tag Details | Monthly Pay
                String outputLine = String.format("%s; Monthly Pay: P%,.2f", doc.tagName(), doc.calculatePay());
                bw.write(outputLine);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file " + fileName + ": " + e.getMessage());
        }
    }
}
