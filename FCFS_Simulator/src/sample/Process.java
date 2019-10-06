package sample;

import javafx.beans.property.SimpleStringProperty;

public class Process {
    private SimpleStringProperty processName,arrivalTime, burstTime, waitTime, turnaroundTime,color,xValue;

    public Process(String processName,String arrivalTime, String burstTime, String waitTime, String turnaroundTime,String color,String xValue) {
        this.processName = new SimpleStringProperty(processName);
        this.arrivalTime = new SimpleStringProperty(arrivalTime);
        this.burstTime = new SimpleStringProperty(burstTime);
        this.waitTime = new SimpleStringProperty(waitTime);
        this.turnaroundTime = new SimpleStringProperty(turnaroundTime);
        this.color = new SimpleStringProperty(color);
        this.xValue = new SimpleStringProperty(xValue);
    }
    public String toString()
    {
        return String.format("%s %s %s %s %s %s %s %s", processName,arrivalTime,burstTime, waitTime, turnaroundTime, color, xValue);
    }

    public String getProcessName() {
        return processName.get();
    }

    public SimpleStringProperty processNameProperty() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName.set(processName);
    }

    public String getArrivalTime() { return arrivalTime.get(); }

    public SimpleStringProperty arrivalTimeProperty() { return arrivalTime; }

    public void setArrivalTime(String arrivalTime) { this.arrivalTime.set(arrivalTime); }

    public String getBurstTime() {
        return burstTime.get();
    }

    public SimpleStringProperty burstTimeProperty() {
        return burstTime;
    }

    public void setBurstTime(String burstTime) {
        this.burstTime.set(burstTime);
    }

    public String getWaitTime() { return waitTime.get(); }

    public SimpleStringProperty waitTimeProperty() { return waitTime; }

    public void setWaitTime(String waitTime) { this.waitTime.set(waitTime); }

    public String getTurnaroundTime() { return turnaroundTime.get(); }

    public SimpleStringProperty turnaroundTimeProperty() { return turnaroundTime; }

    public void setTurnaroundTime(String turnaroundTime) { this.turnaroundTime.set(turnaroundTime); }

    public String getColor() { return color.get(); }

    public SimpleStringProperty colorProperty() { return color; }

    public void setColor(String color) { this.color.set(color); }

    public String getxValue() { return xValue.get(); }

    public SimpleStringProperty xValueProperty() { return xValue; }

    public void setxValue(String xValue) { this.xValue.set(xValue); }
}
