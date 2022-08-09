package com.Sprintbootunit6.model;


import java.util.Objects;

public class DataToBeReturned {  // POJO  Gettters / Setters and default contructor ,

    private  String theData;
    private int aNumber;


    public DataToBeReturned(String theData, int aNumber) {
        this.theData = theData;
        this.aNumber = aNumber;
    }

    public String getTheData() {
        return theData;
    }

    public void setTheData(String theData) {
        this.theData = theData;
    }

    public int getaNumber() {
        return aNumber;
    }

    public void setaNumber(int aNumber) {
        this.aNumber = aNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataToBeReturned that = (DataToBeReturned) o;
        return aNumber == that.aNumber && Objects.equals(theData, that.theData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theData, aNumber);
    }


}
