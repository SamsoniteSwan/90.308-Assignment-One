package com.origamisoftware.teach.agile.junit;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * @author Spencer A Marks
 */
public class SubscriptionPeriodTest {

    private Calendar now;
    private Calendar sixthMonthsFromNow;

    /**
     * This code is used to setup a known state or baseline
     * It is executed before every test
     */
    @Before
    public void setup() {
        // create a known state (also known as a baseline)
        now = Calendar.getInstance();
        sixthMonthsFromNow = Calendar.getInstance();
        sixthMonthsFromNow.add(Calendar.MONTH, 6);
    }

    @Test
    public void testConstruction() {

        // The basic anatomy of a test is this:

        // create a known state - this is done for us already in the setup method

        // change the state, in this case create a new object
        SubscriptionPeriod subscriptionPeriod = new SubscriptionPeriod(now.getTime(), sixthMonthsFromNow.getTime());

        // verify (assert) the change did what we expect
        assertEquals("start date", now.getTime(), subscriptionPeriod.getStartDate());
        assertEquals("end date", sixthMonthsFromNow.getTime(), subscriptionPeriod.getEndDate());
    }


    /**
     * Test the number of days the subscription is in effect
     */
    @Test
    public void testTotalDays() {
        SubscriptionPeriod subscriptionPeriod = new SubscriptionPeriod(now.getTime(), sixthMonthsFromNow.getTime());
        int totalDays = subscriptionPeriod.getTotalDays();
        long differenceInDays = (sixthMonthsFromNow.getTime().getTime() - now.getTime().getTime()) / (1000 * 60 * 60 * 24) + 1;
        assertEquals(totalDays, differenceInDays);
    }


    /**
     * Test the number of months of the subscription
     */
    @Test
    public void testTotalMonths() {
        SubscriptionPeriod subscriptionPeriod = new SubscriptionPeriod(now.getTime(), sixthMonthsFromNow.getTime());
        int totalMonths = subscriptionPeriod.getTotalMonths();
        long differenceInMonth = differenceInMonths(now, sixthMonthsFromNow);
        assertEquals(totalMonths, differenceInMonth);
    }

    /**
     *  Test if a date after the subscription end date properly returns expired as true
     */
    @Test
    public void testHashExpired() {

        Calendar afterSixMonths = Calendar.getInstance();
        afterSixMonths.add(Calendar.MONTH, 7);
        SubscriptionPeriod subscriptionPeriod = new SubscriptionPeriod(now.getTime(), sixthMonthsFromNow.getTime());
        assertTrue(subscriptionPeriod.hasExpired(afterSixMonths.getTime()));
    }


    // it is perfectly fine to have helper methods in test code.

    /**
     * This helper method returns the number of months in the range.
     *
     * @param start starting date
     * @param stop  end date
     * @return  returns the number of months in specified period
     */
    private int differenceInMonths(Calendar start, Calendar stop) {
        int stopYear = stop.get(Calendar.YEAR);
        int startYear = start.get(Calendar.YEAR);
        int stopMonth = stop.get(Calendar.MONTH);
        int startMonth = start.get(Calendar.MONTH);
        return (stopYear - startYear) * 12 + (stopMonth - startMonth);

    }



}
