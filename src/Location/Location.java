package Location;

import Location.LocationAspect;

import OperationsDocumentation.Documentation;

public class Location {

    private boolean ifLegalLocation;

    Documentation doc = new Documentation();

    public Location(){

    }


    public Documentation getDoc(){
        return doc;
    }

    public void setIfLegalLocation(boolean ifLegal){
        this.ifLegalLocation = ifLegal;
    }

    public boolean getIfLegalConnection(){
        return ifLegalLocation;
    }




    public void ifLocationLegal(){

        if(ifLegalLocation){
            System.out.println("Connection country is legal");
        }
        else{
            System.out.println("Connection is available only from Israel");
        }
    }

}
