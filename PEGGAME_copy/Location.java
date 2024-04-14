package PEGGAME_copy;
public class Location {
    private int rows;
    private int cols;


/**
 * Constructor for Location
 * @param rows 
 * @param cols
 */
public Location(int rows,int cols){
    this.rows= rows;
    this.cols = cols;
}
/**
 * Getters for row
 * @return rows
 */
public int getRow(){
    return this.rows;
}
/**
 * Getter for columns
 * @return cols
 */
public int getCols(){
    return this.cols;
}

//hashcode and tostring for location and move
public String toString(){
    // return "There are" + rows + " rows and" + cols + "columns";
    return "(" + rows + " ," + cols + ")";
}

@Override
public boolean equals(Object obj) {
    if (!(obj instanceof Location)) {return false;}
    Location other = (Location) (obj);
    return (this.getRow() == other.getRow() && this.getCols() == other.getCols());
}

}
