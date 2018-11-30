package com.example.nguye.seg2505app.ScheduleClasses;

public class TimeSlot {
    private TimeNode startNode;
    private TimeNode endNode;

    public TimeSlot(int startTime, int endTime, ScheduleState state) {
        TimeNode startNode = new TimeNode(startTime, null, state); // set next after initialization of endNode
        TimeNode endNode = new TimeNode(endTime, null, null);
        startNode.setNext(endNode);;

        this.startNode = startNode;
        this.endNode = endNode;
    }

    public TimeNode getStartNode() { return this.startNode; }
    public TimeNode getEndNode() { return this.endNode; }

    public int getStartTime() { return this.startNode.getTime(); }
    public void setStartTime(int startTime) { this.startNode.setTime(startTime); }

    public int getEndTime() { return this.endNode.getTime(); }
    public void setEndTime(int endTime) { this.endNode.setTime(endTime); }

    public ScheduleState getState() { return this.startNode.getState(); }
    public void setState(ScheduleState state) { this.startNode.setState(state); }

}
