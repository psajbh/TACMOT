package com.jhart.cxe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class StatusFlagTest {
    
    @SuppressWarnings("deprecation")
    @Test
    public void testStatusFlagEquality() throws Exception {
        StatusUser su = new StatusUser();
        su.setStatusFlag(StatusFlag.D);
        assertEquals(su.getStatusFlag(),StatusFlag.D);
        assertNotEquals(su.getStatusFlag(),StatusFlag.A);
    }
    

}
