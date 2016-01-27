package com.origamisoftware.teach.agile.junit;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple Date Range Class
 *
 * @author Spencer A Marks
 */
public class SubscriptionPeriod {

    private Date startDate;
    private Date endDate;

    /**
     * Creates a  SubscriptionPeriod instance
     *
     * @param startDate the starting date of the subscription period
     * @param endDate   the end date of the subscription period
     */
    public SubscriptionPeriod(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the the start date of the subscription
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the the end date of the subscription
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the total Days in the subscription
     */
    public int getTotalDays() {

        int result;
        LocalDate start = convertToLocal(startDate);
        LocalDate end = convertToLocal(endDate);

        result = (int)Duration.between(start.atTime(0,0), end.atTime(0,0)).toDays();
        return result;
    }

    /**
     * @return the total months on the subscription
     */
    public int getTotalMonths() {

        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        calendarStart.setTime(startDate);
        calendarEnd.setTime(endDate);
        int startYear = calendarStart.get(Calendar.YEAR);
        int startMonth = calendarStart.get(Calendar.MONTH);
        int endYear = calendarEnd.get(Calendar.YEAR);
        int endMonth = calendarEnd.get(Calendar.MONTH);

        return ((endYear - startYear) * 12) + (endMonth - startMonth);
    }

    /**
     * Given a date return true if the date comes after the expiration date of this
     * subscription period or false otherwise.
     *
     * @param date a date to consider
     * @return true if true if the date comes after the expiration date of this
     * subscription period or false otherwise.
     */
    public boolean hasExpired(Date date) {

        boolean result = false;
        if (date.after(endDate)) {
            result = true;
        }
        return result;

    }

    private LocalDate convertToLocal (Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

}
