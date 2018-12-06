package com.example.nguye.seg2505app.ScheduleClasses;

import android.content.Context;

import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.CustomSchedule;
import com.example.nguye.seg2505app.Storables.DefaultSchedule;
import com.example.nguye.seg2505app.Storables.Storable;
import com.example.nguye.seg2505app.Utilities.FormatValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DailySchedule {

    private TimeNode head;
    private int size;

    /**
     * Empty constructor
     * Used tu build a new DailySchedule from 0 when merging a DailySchedule with a TimeSlot.
     */
    public DailySchedule() {
        head = new TimeNode(0, null, null);
//		head.prev = head.next = head;
        size = 0;
    }

    /**
     * Constructor
     * Creates a 4-nodes schedule using the 2 nodes from the DefaultSchedule and adding 2 extreme nodes.
     * Used to create the starting point to build the final DailySchedule.
     * @param startTime
     * @param endTime
     * @param state
     */
    public DailySchedule(int startTime, int endTime, ScheduleState state) {
        head = new TimeNode(0, null, null); // set next after initialization of node1
        TimeNode node1 = new TimeNode(0, null, ScheduleState.UNAVAILABLE); // set next after initialization of node2
        TimeNode node2 = new TimeNode(startTime, null, state); // set next after initialization of node3
        TimeNode node3 = new TimeNode(endTime, null, ScheduleState.UNAVAILABLE); // set next after initialization of node4
        TimeNode node4 = new TimeNode(1440, null, null);

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        size = 4;
    }

    public void append(TimeNode newTimeNode) {
        TimeNode last = this.head;
        while (last.getNext() != null) { // get to the end of the chain
            last = last.getNext();
        }
        last.setNext(newTimeNode);
        newTimeNode.setNext(null);
    }

//	/**
//	 * Get the first TimeNode of the com.example.vincent.testing.ScheduleManager.DailySchedule.
//	 * @return
//	 */
//	public TimeNode getFirst() {
//		return this.head.next;
//	}

    /**
     * Get the last TimeNode of the com.example.vincent.testing.ScheduleManager.DailySchedule.
     * @return
     */
    public TimeNode getLast() {
        TimeNode last = head;
        while (last.getNext() != null) {
            last = last.getNext();
        }
        return last;
    }

    public TimeNode extractFirst() {
        if (this.head.getNext() == null) {
            return null;
        } else {
            TimeNode first = this.head.getNext();
            this.head.setNext(first.getNext());
            first.setNext(null);
            return first;
        }
    }

    public DailySchedule merge(TimeSlot timeSlot) {
        // Initialize a blank DailySchedule.
        DailySchedule newDS = new DailySchedule();

        // Append all the TimeNodes that are earlier than the startNode of the TimeSlot.
        while (this.head.getNext().getTime() < timeSlot.getStartTime()) {
            newDS.append(this.extractFirst());
        }

        // If a DailySchedule's TimeNode and a TimeSlot's TimeNode have the same time, the TimeSlot has priority
        if (timeSlot.getStartTime() == this.head.getNext().getTime()) {
            // Append the timeSlot's startNode and remove the next TimeNode of the current DailySchedule.
            newDS.append(timeSlot.getStartNode());
            timeSlot.getEndNode().setState(this.extractFirst().getState()); // Remove it but keep its state.
        } else {
            newDS.append(timeSlot.getStartNode());
        }

        // Ignore all subsequent TimeNodes that are earlier or equal to the TimeSlot's endTime,
        //	but copy the state of the last ignored node in the endTime TimeNode.
        while (this.head.getNext().getTime() <= timeSlot.getEndTime()) {
            timeSlot.getEndNode().setState(this.head.getNext().getState()); // Copy the state of the last ignored node.
            this.extractFirst();
        }
        newDS.append(timeSlot.getEndNode());

        // Append the rest of the TimeNodes of the com.example.vincent.testing.ScheduleManager.DailySchedule
        while (this.head.getNext() != null) {
            newDS.append(this.extractFirst());
        }

        newDS.cleanUp();
        newDS.setSize(newDS.getSize());
        return newDS;
    }

    /**
     * Check if there is any overlap between a booked time slot in the daily schedule and the specified time slot.
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean isBookedBetween(int startTime, int endTime) {
        TimeNode currentNode = this.head;
        if (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }

        // Find the first BOOKED node since the others have no interest.
        while (currentNode.getNext() != null) {
            if (currentNode.getState() == ScheduleState.BOOKED) {
                break;
            }
            currentNode = currentNode.getNext();
        }
        // Check if there is any overlap between the specified time slot and a BOOKED state.
        while (currentNode.getNext() != null) {
            if (currentNode.getState() == ScheduleState.BOOKED) {
                if (((currentNode.getTime() >= startTime) // The currentNode is between startTime
                        && (currentNode.getTime() < endTime)) // and endTime, or
                        || ((currentNode.getTime() < startTime) // startTime is between the 2 nodes
                        && (currentNode.getNext().getTime() > startTime))) {
                    return  true;
                }
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public DailySchedule cleanUp() {
        TimeNode currentNode = this.head;
        TimeNode nextNode = currentNode.getNext();
        while (nextNode != null) {
            if (currentNode.getState() == nextNode.getState()) {
                currentNode.removeNext();
                nextNode = currentNode.getNext();
            } else {
                currentNode = nextNode;
                nextNode = nextNode.getNext();
            }
        }
        return this;
    }

    /**
     *
     * @param providerID The ID of the provider that the schedule is associated to
     * @param dateString A date in the format "yyyy-mm-dd"
     * @return
     */
    public DailySchedule generate(Context context, int providerID, String dateString) {
        // Generate the DailySchedule from the data in the table DefaultSchedules and CustomSchedules
        //  1. Initialize a new DailySchedule by using the right fields from DefaultSchedules
        //  2. For each record in CustomSchedule that meet the right conditions, merge one by one with the DailySchedule.

        DailySchedule schedule;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            calendar.setTime(df.parse(dateString));
            String startTimeField = "";
            String endTimeField = "";
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY:
                    startTimeField = DefaultSchedule.COL_SUNDAYSTART;
                    endTimeField = DefaultSchedule.COL_SUNDAYEND;
                    break;
                case Calendar.MONDAY:
                    startTimeField = DefaultSchedule.COL_MONDAYSTART;
                    endTimeField = DefaultSchedule.COL_MONDAYEND;
                    break;
                case Calendar.TUESDAY:
                    startTimeField = DefaultSchedule.COL_TUESDAYSTART;
                    endTimeField = DefaultSchedule.COL_TUESDAYEND;
                    break;
                case Calendar.WEDNESDAY:
                    startTimeField = DefaultSchedule.COL_WEDNESDAYSTART;
                    endTimeField = DefaultSchedule.COL_WEDNESDAYEND;
                    break;
                case Calendar.THURSDAY:
                    startTimeField = DefaultSchedule.COL_THURSDAYSTART;
                    endTimeField = DefaultSchedule.COL_THURSDAYEND;
                    break;
                case Calendar.FRIDAY:
                    startTimeField = DefaultSchedule.COL_FRIDAYSTART;
                    endTimeField = DefaultSchedule.COL_FRIDAYEND;
                    break;
                case Calendar.SATURDAY:
                    startTimeField = DefaultSchedule.COL_SATURDAYSTART;
                    endTimeField = DefaultSchedule.COL_SATURDAYEND;
                    break;
            }

            String effDateQuery = "SELECT " + DefaultSchedule.COL_EFFECTIVEDATE
                    + " FROM " + DefaultSchedule.TABLE_NAME
                    + " WHERE " + DefaultSchedule.COL_PROVIDER + " = " + providerID;
            System.out.println(effDateQuery);

            // 1. get all the dates
            // 2. convert them to timeStrings, as well as the current date
            // 3. Keep the max date that is still lower than today (or equal)

            ArrayList<String[]> effectiveDates = Storable.select(context, effDateQuery, 1);
            long[] dateLong = new long[effectiveDates.size()];
            for (int i = 0; i < dateLong.length; i++) {
                dateLong[i] = FormatValue.dateToLong(effectiveDates.get(i)[0]);
            }
            long currentDate = FormatValue.dateToLong(dateString);
            long currentEffDate = 0;
            long tempEffDate;
            for (int i = 0; i < dateLong.length; i++) {
                tempEffDate = dateLong[i];
                if ((tempEffDate > currentEffDate) && (tempEffDate <= currentDate)) {
                    currentEffDate = tempEffDate;
                }
            }
            String currentEffDateString = FormatValue.longToDate(currentEffDate);
            String effDefaultScheduleQuery = "SELECT " + startTimeField + ", " + endTimeField
                    + " FROM " + DefaultSchedule.TABLE_NAME
                    + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE + " = \"" + currentEffDateString + "\""
                    + " AND " + DefaultSchedule.COL_PROVIDER + " = " + providerID;
            System.out.println(effDefaultScheduleQuery);

            ArrayList<String[]> currentEffSchedule = Storable.select(context, effDefaultScheduleQuery, 2);

            // If a schedule has been found
            if (currentEffSchedule.size() > 0) {
                String startTime = currentEffSchedule.get(0)[0];
                String endTime = currentEffSchedule.get(0)[1];
                schedule = new DailySchedule(Integer.parseInt(startTime), Integer.parseInt(endTime), ScheduleState.AVAILABLE);

                // For each record in CustomSchedule that meet the right conditions, merge one by one with the DailySchedule.
                String query = "SELECT " + CustomSchedule.COL_START + ", " + CustomSchedule.COL_END + ", "
                        + CustomSchedule.COL_AVAILABILITY + " FROM " + CustomSchedule.TABLE_NAME
                        + " WHERE " + CustomSchedule.COL_DATE + " = \"" + dateString + "\" AND " + CustomSchedule.COL_PROVIDER
                        + " = " + providerID;
                System.out.println(query);

                ArrayList<String[]> customSchedules = Storable.select(context, query, 3);
                int timeSlotStart;
                int timeSlotEnd;
                ScheduleState state;
                for (String[] record : customSchedules) {
                    timeSlotStart = Integer.parseInt(record[0]);
                    timeSlotEnd = Integer.parseInt(record[1]);
                    state = ScheduleState.valueOf(record[2]);
                    TimeSlot timeSlot = new TimeSlot(timeSlotStart, timeSlotEnd, state);
                    // TODO check if the problem is fixed
                    schedule = schedule.merge(timeSlot); // Merge
                    System.out.println(timeSlotStart + " " + timeSlotEnd + " " + state);
                }
                return schedule;
            }
            return new DailySchedule();
        } catch (ParseException e) {
            return new DailySchedule();
        }
    }

    /**
     * Store every node in an ArrayList for easy display in RecyclerViews
     * @return
     */
    public ArrayList<TimeNode> toArrayList() {
        ArrayList<TimeNode> scheduleArray = new ArrayList<>();
        TimeNode currentNode = this.head;
//        TimeNode nextNode = currentNode.getNext();
        while (currentNode.getNext() != null) {
            scheduleArray.add(currentNode.getNext());
            currentNode = currentNode.getNext();
        }
        // Remove the last node from the list,
        // since the state will always be null and the time 24:00
        scheduleArray.remove(scheduleArray.size() - 1);
        return scheduleArray;
    }

    public int getSize() {
        int size = 0;
        TimeNode node = this.head;
        while (node.getNext() != null) {
            node = node.getNext();
            size++;
        }
        return size;
    }

    public void setSize(int size) { this.size = size; }

    public String toString() {
        TimeNode timeNode = this.head.getNext();
        String schedule = "Schedule's size: " + this.size + "\n";
        while (timeNode != null) {
            schedule = schedule + timeNode.toString();
            timeNode = timeNode.getNext();
        }
        return schedule;
    }
}
