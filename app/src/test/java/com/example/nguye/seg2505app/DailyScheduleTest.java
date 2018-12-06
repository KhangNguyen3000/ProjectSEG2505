package com.example.nguye.seg2505app;

import com.example.nguye.seg2505app.ScheduleClasses.DailySchedule;
import com.example.nguye.seg2505app.ScheduleClasses.ScheduleState;
import com.example.nguye.seg2505app.ScheduleClasses.TimeNode;
import com.example.nguye.seg2505app.ScheduleClasses.TimeSlot;

import org.junit.Test;
import static org.junit.Assert.*;

public class DailyScheduleTest {

    @Test
    public void DailySchedule_isBookedBetween() {
        DailySchedule schedule = new DailySchedule(480, 960, ScheduleState.BOOKED);
        assertFalse(schedule.isBookedBetween(240, 360)); // (tss < dss) && (tse < dss)
        assertFalse(schedule.isBookedBetween(240, 480)); // (tss < dss) && (tse == dss)
        assertTrue(schedule.isBookedBetween(240, 720)); // (tss < dss) && (tse < dse)
        assertTrue(schedule.isBookedBetween(240, 960)); // (tss < dss) && (tse == dse)
        assertTrue(schedule.isBookedBetween(240, 1080)); // (tss < dss) && (tse > dse)
        assertTrue(schedule.isBookedBetween(480, 720)); // (tss == dss) && (tse < dse)
        assertTrue(schedule.isBookedBetween(480, 960)); // (tss == dss) && (tse == dse)
        assertTrue(schedule.isBookedBetween(480, 1080)); // (tss == dss) && (tse > dse)
        assertTrue(schedule.isBookedBetween(720, 900)); // (tss > dss) && (tse < dse)
        assertTrue(schedule.isBookedBetween(720, 960)); // (tss > dss) && (tse == dse)
        assertTrue(schedule.isBookedBetween(720, 1080)); // (tss > dss) && (tse > dse)
        assertFalse(schedule.isBookedBetween(960, 1080)); // (tss == dse) && (tse > dse)
        assertFalse(schedule.isBookedBetween(1080, 1200)); // (tss > dse) && (tse > dse)

        DailySchedule schedule2 = new DailySchedule(480, 960, ScheduleState.AVAILABLE);
        assertFalse(schedule2.isBookedBetween(0, 1440));
    }


    // TODO other tests related to schedules

    @Test
    public void DailySchedule_sfsd() {
        DailySchedule schedule = new DailySchedule(480, 960, ScheduleState.AVAILABLE);
        TimeSlot timeSlot = new TimeSlot(480, 540, ScheduleState.BOOKED);
        schedule = schedule.merge(timeSlot);
        System.out.println(schedule);
    }
}
