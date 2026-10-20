import java.util.ArrayList;

public class DinoPark {
    private ArrayList<SafetyCheckable> habitats;

    public DinoPark() {
        this.habitats = new ArrayList<>();
    }

    public void addAttraction(SafetyCheckable attraction) {
        if (attraction == null) {
            throw new IllegalArgumentException("Attraction cannot be null");
        }
        habitats.add(attraction);
    }

    public String generateSafetyReport() {
        String report = "=== SAFETY REPORT ===";
        for (SafetyCheckable attraction : habitats) {
            report += "\n" + attraction.toString()
                + "\nHuman Safety Violations: " + attraction.countHumanSafetyViolations()
                + "\nDinosaur Safety Violations: " + attraction.countDinoSafetyViolations()
                + "\n";
        }
        return report;
    }
    
}
