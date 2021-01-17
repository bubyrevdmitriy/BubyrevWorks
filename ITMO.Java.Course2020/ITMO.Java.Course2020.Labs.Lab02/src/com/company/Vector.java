package com.company;

public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public double vectorLength(){
        double result=Math.sqrt((Math.pow(this.x, 2))+(Math.pow(this.y, 2))+(Math.pow(this.z, 2)));
        return result;
    }

    public double vectorScalarMultiple(Vector anotherVector){
        double result = (this.x*anotherVector.x)+(this.y*anotherVector.y)+(this.z*anotherVector.z);
        return result;
    }

    public Vector VectorMultiple(Vector anotherVector) {

        Vector Vector = new Vector((this.y*anotherVector.z-this.z*anotherVector.y),
                (this.z*anotherVector.x-this.x*anotherVector.z),
                (this.x*anotherVector.y-this.y*anotherVector.x));
        return Vector;
    }


    public double vectorAngle(Vector anotherVector){
        double abScalar = ((this.x*anotherVector.x)+
        (this.y*anotherVector.y)+
                (this.z*anotherVector.z));
        double aModule = Math.abs(this.vectorLength());
        double bModule = Math.abs(anotherVector.vectorLength());
        double result = abScalar/(aModule*bModule);
        return result;
    }


    public Vector VectorSum(Vector anotherVector) {
        Vector Vector = new Vector((this.x+anotherVector.x),
                (this.y+anotherVector.y),
                (this.z+anotherVector.z));
        return Vector;
    }

    public Vector VectorDif(Vector anotherVector) {
        Vector Vector = new Vector((this.x-anotherVector.x),
                (this.y-anotherVector.y),
                (this.z-anotherVector.z));
        return Vector;
    }

    static Vector[] ArrayVectors(int n){
        Vector[] vectors = new Vector[n];
        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector((10*Math.random()),
                (10*Math.random()),
                (10*Math.random()));
            //10*Math.random();
        }
        return vectors;
    }


}
