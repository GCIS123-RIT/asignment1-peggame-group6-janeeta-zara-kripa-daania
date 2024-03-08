package peggame;

public class Move {
    private Location from;
    private Location to;

/**
 * 
 * @param from
 * @param to
 */
public Move(Location from,Location to){
    this.from = from;
    this.to = to;
}
public Location getFrom(){
    return this.from;

}
public Location getTo(){
    return this.to;

}
public String toString(){
    // return "The peg will move from " + from + ", initial location to " + to + ",final location";
    return "Move from " + from + ", to " + to;
}

    
}
