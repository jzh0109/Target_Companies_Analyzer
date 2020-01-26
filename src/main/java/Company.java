public class Company {
    private String name;
    private String status;
    private long revenue;
    private String location;

    public Company(String name, String status, long revenue, String location) {
        this.name = name;
        this.status = status;
        this.revenue = revenue;
        this.location = location;
    }

    public Company() {
        name = "Default Company";
        status = "";
        revenue = 0;
        location = "Jinwoo-ville";
    }

    public String getName() {
        return name;
    }
    public String getStatus() {
        return status;
    }
    public long getRevenue() {
        return revenue;
    }
    public String getLocation() {
        return location;
    }
}
