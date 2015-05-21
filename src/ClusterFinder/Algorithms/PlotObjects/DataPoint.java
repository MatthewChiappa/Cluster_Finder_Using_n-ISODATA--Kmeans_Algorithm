package ClusterFinder.Algorithms.PlotObjects;

public class DataPoint {
    
    // variables
    private final double x;
    private final double y;
    private final double z;
    public double[] extraParam;
    private int clustNum;
    
    public DataPoint(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.extraParam = null;
    }
    
    // init
    public DataPoint(double x, double y, double z, double[] extraParam) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.extraParam = extraParam;
    }
    
    // returns x
    public double getX() {
        return x;
    }
    
    // returns y
    public double getY() {
        return y;
    }
    
    // returns z
    public double getZ() {
        return z;
    }
    
    public int getClust(){
        return clustNum;
    }
    
    public void setClust(int num){
        this.clustNum = num;
    }
    
    // returns the extra parameters in however many dimensions
    public double[] getExtraParams() {
        return extraParam;
    }
    
    // compares this data point to another
    public boolean compareTo(DataPoint newPoint) {
        return x == newPoint.getX() && y == newPoint.getY()
                && z == newPoint.getZ();
    }
    
    public boolean similarTo(DataPoint newPoint, int droppedNum) {
        boolean similar;
        
        switch (droppedNum){
            case 0:
                if(y == newPoint.getY() && z == newPoint.getZ())
                    for (int i  = 0; i < extraParam.length
                            && i < newPoint.getExtraParams().length; i++)
                        if (extraParam[i] != newPoint.getExtraParams()[i])
                            return false;
                else
                    return false;        
                break;
            case 1:
                if(x == newPoint.getX() && z == newPoint.getZ())
                    for (int i  = 0; i < extraParam.length
                            && i < newPoint.getExtraParams().length; i++)
                        if (extraParam[i] != newPoint.getExtraParams()[i])
                            return false;
                else
                    return false;
                break;
            case 2:
                if(x == newPoint.getX() && y == newPoint.getY())
                    for (int i  = 0; i < extraParam.length
                            && i < newPoint.getExtraParams().length; i++)
                        if (extraParam[i] != newPoint.getExtraParams()[i])
                            return false;
                else
                    return false;
                break;
            default:
                if(y == newPoint.getY() && z == newPoint.getZ())
                    for (int i  = 0; i < extraParam.length
                            && i < newPoint.getExtraParams().length; i++)
                        if (extraParam[i] != newPoint.getExtraParams()[i])
                            return false;
                else
                    return false;
                break;
        }
        
        similar = true;
        return similar;
    }
    
    public double distanceToPoint(DataPoint point2) {
        int SIZE = point2.getExtraParams().length;
        double[] center = new double[SIZE+3];
        
        center[0] = this.getX();
        center[1] = this.getY();
        center[2] = this.getZ();
        
        double[] newPt = new double[SIZE+4];
        
        newPt[0] = point2.getX();
        newPt[1] = point2.getY();
        newPt[2] = point2.getZ();
        
        double[] extra = new double[SIZE];
        
        if(3+SIZE > 3) {
            for(int i = 0; i < SIZE; i++) {
                extra[i] = point2.getExtraParams()[i];
                center[i+3] = this.getExtraParams()[i];
            }
            
            System.arraycopy(extra, 0, newPt, 3, extra.length);
        }
        
        double sum = 0, sumSqr;
        
        for (int i = 0 ; i < SIZE+3; i++){
            if(Double.isNaN(newPt[i]))
                newPt[i] = 0;
            else if(Double.isNaN(center[i]))
                center[i] = 0;
        
            
            sumSqr = newPt[i] - center[i];
            sumSqr = Math.pow(sumSqr, 2);
            sum += sumSqr;
        }
        
        return Math.sqrt(sum); 
    }
    
    // print method for debugging (only first 3 params)
    public String printPoint() {
        String extra = new String();
        
        for (int i = 0; i < extraParam.length; i++)
            extra += extraParam[i] + "\t";
        
        return (x + "\t" + y + "\t" + z + "\t" + extra);
    }
    
}
