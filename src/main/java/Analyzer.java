import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Analyzer {

    private static String name;
    private static String status;
    private static long revenue;
    private static String location;
    private static JSONArray companyList;

    public static void readJson() {
        JSONParser parser = new JSONParser();
        try (FileReader readFile = new FileReader("Company targets.json")) {
            Object temp = parser.parse(readFile);
            companyList = (JSONArray) temp;
            //companyList.forEach(company -> getCompany((JSONObject) company));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getName(JSONObject company) {
        name = company.get("Name").toString();
        return name;
    }

    public static String getStatus(JSONObject company) {
        status = company.get("Status").toString();
        return status;
    }

    public static long getRevenue(JSONObject company) {
        revenue = Long.parseLong(company.get("Revenue").toString());
        return revenue;
    }

    public static String getLocation(JSONObject company) {
        location = company.get("Location").toString();
        return location;
    }

    public static JSONArray getCompanies() {
        return companyList;
    }

    public static CompanyStatus getStatusCounts(JSONArray companies) {
        int researchingCount = 0;
        int approvedCount = 0;
        int declinedCount = 0;
        for (int i = 0; i < companies.size(); i++) {
            JSONObject company = (JSONObject) companies.get(i);
            String currentStat = getStatus(company);
            if (currentStat.equals("Researching")) {
                researchingCount++;
            }
            if (currentStat.equals("Approved")) {
                approvedCount++;
            }
            if (currentStat.equals("Declined")) {
                declinedCount++;
            }
        }
        return new CompanyStatus(researchingCount, approvedCount, declinedCount);
    }

    public static Company getClosestRevenueCompany(JSONArray companies, long desiredRevenue) {
        long lowestDifference = getRevenue((JSONObject) companies.get(0)), temp;
        int index = 0;
        //System.out.println(desiredRevenue);
        for (int i = 0; i < companies.size(); i++) {
            JSONObject company = (JSONObject) companies.get(i);
            temp = Math.abs(desiredRevenue - getRevenue(company));
            if (temp < lowestDifference) {
                lowestDifference = temp;
                //closestCompany = new Company(getName(company), getStatus(company), getRevenue(company), getLocation(company));
                index = i;
            }
        }
        JSONObject foundCompany = (JSONObject) companies.get(index);
        return new Company(getName(foundCompany), getStatus(foundCompany), getRevenue(foundCompany), getLocation(foundCompany));
    }

    public static void main(String[] args) {
        readJson();
        CompanyStatus statusCount = getStatusCounts(getCompanies());
        System.out.println(statusCount.toString());
        Company closestCompany = getClosestRevenueCompany(getCompanies(), 6851000000L);
        System.out.println(closestCompany.getName());
    }
}
