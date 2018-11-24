package com.example.nguye.seg2505app;

import com.example.nguye.seg2505app.Utilities.FormatValue;

import org.junit.Test;
import static org.junit.Assert.*;

public class FormatValueTest {

    @Test
    public void FormatValue_minToTimeString() {
        assertEquals("0:00", FormatValue.minToTimeString(0));
        assertEquals("6:00", FormatValue.minToTimeString(360));
        assertEquals("12:00", FormatValue.minToTimeString(720));
        assertEquals("12:34", FormatValue.minToTimeString(754));
    }

    @Test
    public void FormatValue_timeStringToMin() {
        assertEquals(-1, FormatValue.timeStringToMin("0:0"));
        assertEquals(-1, FormatValue.timeStringToMin("0:"));
        assertEquals(-1, FormatValue.timeStringToMin(":0"));
        assertEquals(-1, FormatValue.timeStringToMin("0"));
        assertEquals(0, FormatValue.timeStringToMin("0:00"));
        assertEquals(0, FormatValue.timeStringToMin("00:00"));
        assertEquals(754, FormatValue.timeStringToMin("12:34"));
        assertEquals(754, FormatValue.timeStringToMin("12:3456"));
        assertEquals(6034, FormatValue.timeStringToMin("100:3456"));
    }

}
