package Location;

import Location.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public aspect LocationAspect {

    private static final String LOCATION_API = "https://geo.ipify.org/api/v1?apiKey=at_ht4MAHke8XjItGwAxHIUtyrJELJqC";
    private static final String COUNTRY_KEY = "country";

    String country;

    pointcut locationCheck(Location location) : call(public void Location.ifLocationLegal()) && target(location);
    before(Location location) : locationCheck(location){

        try{
            System.out.println("Checking location...");
            String dataFromServer = getDataFromServer();
            country = getCountryFromData(dataFromServer);

            if(country.equals("IL")){
                location.setIfLegalLocation(true);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Url connection error");
            System.exit(1);
        }
    }


    private static String getDataFromServer() throws IOException {
        URL url = new URL(LOCATION_API);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder data = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            data.append(inputLine);
        in.close();
        return data.toString();
    }



    private static String getCountryFromData(String data) {
        int keyEntryIndex = data.indexOf(COUNTRY_KEY);
        int nextInd = data.indexOf(':', keyEntryIndex);
        int valueStartIndex = data.indexOf('"', nextInd);
        int valueEndIndex = data.indexOf(',', valueStartIndex);

        return data.substring(valueStartIndex+1, valueEndIndex-1);
    }




    pointcut locationDoc(Location location) :
            call(public void Location.ifLocationLegal()) && target(location);

    after(Location location) : locationDoc(location){

        String curTime = location.getDoc().convertion(System.currentTimeMillis());

        String legalLocation = "\n" + curTime + "    Location " +  country;

        String unlegalLocation = "\n" + curTime + "    Location country is illegal";

        if(country.equals("IL")) {
            location.getDoc().setContentForDocumentation(legalLocation);
        }
        else{
            location.getDoc().setContentForDocumentation(unlegalLocation);
        }

    }

}



