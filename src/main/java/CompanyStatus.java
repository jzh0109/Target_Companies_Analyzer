public class CompanyStatus {
    private int researching;
    private int approved;
    private int declined;

    public CompanyStatus(int researching, int approved, int declined) {
        this.researching = researching;
        this.approved = approved;
        this.declined = declined;
    }

    public String toString() {
        return "Researching: " + researching + "\nApproved: " + approved + "\nDeclined: " + declined;
    }
}
