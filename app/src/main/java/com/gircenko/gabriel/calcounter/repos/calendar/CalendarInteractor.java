package com.gircenko.gabriel.calcounter.repos.calendar;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.models.StartOrEnd;
import com.gircenko.gabriel.calcounter.ui.adapters.CaloriesPagerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class CalendarInteractor implements ICalendarInteractor {

    private Calendar calendarStart;
    /** Used for search */
    private Calendar calendarEnd;
    private Date dateStart;
    /** Used for search */
    private Date dateEnd;

    private static final long DAY = 1000 * 60 * 60 * 24;

    public CalendarInteractor() {}

    /**{@inheritDoc}*/
    @Override
    public void setDate(StartOrEnd startOrEnd, int year, int month, int day) {
        if (startOrEnd == StartOrEnd.START) {
            calendarStart.set(year, month, day);
            dateStart = calendarStart.getTime();

        } else {
            calendarEnd.set(year, month, day);
            dateEnd = calendarEnd.getTime();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void setTime(StartOrEnd startOrEnd, int hour, int minute) {
        if (startOrEnd == StartOrEnd.START) {
            calendarStart.set(Calendar.HOUR_OF_DAY, hour);
            calendarStart.set(Calendar.MINUTE, minute);
            dateStart = calendarStart.getTime();

        } else {
            calendarEnd.set(Calendar.HOUR_OF_DAY, hour);
            calendarEnd.set(Calendar.MINUTE, minute);
            dateEnd = calendarEnd.getTime();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void initializeDateModels() {
        calendarStart = Calendar.getInstance();
        dateStart = calendarStart.getTime();
        calendarEnd = Calendar.getInstance();
        dateEnd = calendarEnd.getTime();
    }

    /**{@inheritDoc}
     * @param startOrEnd*/
    @Override
    public String getDate(StartOrEnd startOrEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        if (startOrEnd == StartOrEnd.START) return sdf.format(dateStart);
        else return sdf.format(dateEnd);
    }

    /**{@inheritDoc}
     * @param startOrEnd*/
    @Override
    public String getTime(StartOrEnd startOrEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
        if (startOrEnd == StartOrEnd.START) return sdf.format(dateStart);
        else return sdf.format(dateEnd);
    }

    /**{@inheritDoc}
     * @param startOrEnd*/
    @Override
    public int getYear(StartOrEnd startOrEnd) {
        if (startOrEnd == StartOrEnd.START) {
            calendarStart.setTime(dateStart);
            return calendarStart.get(Calendar.YEAR);

        } else {
            calendarEnd.setTime(dateEnd);
            return calendarEnd.get(Calendar.YEAR);
        }
    }

    /**{@inheritDoc}
     * @param startOrEnd*/
    @Override
    public int getMonth(StartOrEnd startOrEnd) {
        if (startOrEnd == StartOrEnd.START) {
            calendarStart.setTime(dateStart);
            return calendarStart.get(Calendar.MONTH);

        } else {
            calendarEnd.setTime(dateEnd);
            return calendarEnd.get(Calendar.MONTH);
        }
    }

    /**{@inheritDoc}
     * @param startOrEnd*/
    public int getDAY(StartOrEnd startOrEnd) {
        if (startOrEnd == StartOrEnd.START) {
            calendarStart.setTime(dateStart);
            return calendarStart.get(Calendar.DAY_OF_MONTH);

        } else {
            calendarEnd.setTime(dateEnd);
            return calendarEnd.get(Calendar.DAY_OF_MONTH);
        }
    }

    /**{@inheritDoc}
     * @param startOrEnd*/
    @Override
    public int getHour(StartOrEnd startOrEnd) {
        if (startOrEnd == StartOrEnd.START) {
            calendarStart.setTime(dateStart);
            return calendarStart.get(Calendar.HOUR_OF_DAY);

        } else {
            calendarEnd.setTime(dateEnd);
            return calendarEnd.get(Calendar.HOUR_OF_DAY);
        }
    }

    /**{@inheritDoc}
     * @param startOrEnd*/
    @Override
    public int getMinute(StartOrEnd startOrEnd) {
        if (startOrEnd == StartOrEnd.START) {
            calendarStart.setTime(dateStart);
            return calendarStart.get(Calendar.MINUTE);

        } else {
            calendarEnd.setTime(dateEnd);
            return calendarEnd.get(Calendar.MINUTE);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public int getDayInLastWeekByDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date dateDate = null;
        try {
            dateDate = sdf.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dateDate != null) {
            Calendar todayDateCalendar = getTodaysCalendar();
            Date now = new Date();
            long todaysDiff = now.getTime() - todayDateCalendar.getTime().getTime();
            long diff = now.getTime() - dateDate.getTime();
            if (todaysDiff < diff) {
                return CaloriesPagerAdapter.PAGE_COUNT - 1 - millisecondsToDays(todayDateCalendar.getTime().getTime() - dateDate.getTime());

            } else if (todaysDiff == diff) {
                return CaloriesPagerAdapter.PAGE_COUNT - 1;

            } else {
                return -1;
            }

        } else {
            return -1;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public String getDateByPage(int page) {
        // today
        Calendar date = getTodaysCalendar();
        date.add(Calendar.DAY_OF_MONTH, - (CaloriesPagerAdapter.PAGE_COUNT - 1 - page));

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        return sdf.format(date.getTime());
    }

    @Override
    public boolean isDateInRange(String date, String dateStart, String dateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date parameterDate = null;
        Date parameterDateStart = null;
        Date parameterDateEnd = null;
        try {
            parameterDate = sdf.parse(date);
            parameterDateStart = sdf.parse(dateStart);
            parameterDateEnd = sdf.parse(dateEnd);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (parameterDate.before(parameterDateEnd) || parameterDate.equals(parameterDateEnd))
                && (parameterDate.after(parameterDateStart) || parameterDate.equals(parameterDateStart));
    }

    @Override
    public boolean isTimeInRange(String time, String timeStart, String timeEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
        Date parameterDate = null;
        Date parameterDateStart = null;
        Date parameterDateEnd = null;
        try {
            parameterDate = sdf.parse(time);
            parameterDateStart = sdf.parse(timeStart);
            parameterDateEnd = sdf.parse(timeEnd);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (parameterDate.before(parameterDateEnd) || parameterDate.equals(parameterDateEnd))
                && (parameterDate.after(parameterDateStart) || parameterDate.equals(parameterDateStart));
    }

    private static Calendar getTodaysCalendar() {
        // today
        Calendar gregorianCalendar = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);
        gregorianCalendar.set(Calendar.MILLISECOND, 0);

        return gregorianCalendar;
    }

    private static int millisecondsToDays(long milliseconds) {
        return (int) (milliseconds / DAY);
    }
}
