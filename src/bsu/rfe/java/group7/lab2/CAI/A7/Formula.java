package bsu.rfe.java.group7.lab2.CAI.A7;

public class Formula {
    private Double sum;

    public Formula(){
        sum = 0.0;
    }

    public Double calculate1(Double x, Double y, Double z){
        return(Math.pow(Math.pow(Math.log(1+z), 2)+Math.cos(3.14*Math.pow(y,3)),1./4 ))/
        		(Math.pow((Math.cos(Math.exp(x))+Math.pow(1/x, 1/2))+
        				Math.exp(Math.pow(x,2)),Math.sin(x)));
    }

    public Double calculate2 (Double x, Double y, Double z){
        return (Math.pow(Math.sin(Math.pow(z, y)), 2))/(Math.pow(1+Math.pow(x, 3), 1/2));
    }

    public Double getSum(){
        return sum;
    }

    public void setSum(Double x) {
        sum = x;
    }

    public void plusSum(Double result){
        sum+=result;
    }
}