package org.bosbec.createspecificationexample;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationFieldType;

import java.util.Date;

public class Program {
    public static void main(String[] arguments) {
        // List current specifications
        // String specifications = SpecificationsHelper.list();

        // System.out.println(specifications);

        // Create new specification
        DateTime currentDateTime = DateTime.now(DateTimeZone.UTC);
        DateTime periodStart = new DateTime(
                currentDateTime.getYear(),
                currentDateTime.getMonthOfYear(),
                1,
                0,
                0,
                0,
                DateTimeZone.UTC);

        DateTime periodEnd = periodStart.withFieldAdded(
                DurationFieldType.months(),
                1);

        System.out.println(periodStart.toString());
        System.out.println(periodEnd.toString());

        // Start a listener and that stuff
        String listenerUrl = "";

        SpecificationsHelper.create(periodStart, periodEnd, listenerUrl);
        // Wait for update

        // Fetch new specification


    }
}