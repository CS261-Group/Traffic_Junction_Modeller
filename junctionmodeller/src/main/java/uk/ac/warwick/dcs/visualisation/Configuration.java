package uk.ac.warwick.dcs.visualisation;

class Road{
    public Road(){

    }
}

// might benefit from the builder pattern (since it's gonna be a fat class)
class Configuration{
    // roads named after the direction they emerge from (e.g. northRoad is from North, heading South)
    private Road northRoad; 
    private Road southRoad; 
    private Road westRoad; 
    private Road eastRoad;

    public Configuration(){
        this.northRoad = new Road();
        this.southRoad = new Road();
        this.westRoad = new Road();
        this.eastRoad = new Road();
    }

    
}