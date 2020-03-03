package com.pky.easycms.util

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*


/**
 * Created by Dae-Hyeon Daniel, Lee @ LINE PLUS on 12/07/2017.
 *
 * @author Dae-Hyeon Daniel, Lee
 */
object TimeUtils {

    /**
     *
     * Convert the time-zone of a date time from system default to specific time-zone.
     *
     * @param   dateTime    A date time which you want to change time-zone
     * @param   zoneIdTo    A Id. of time-zone which you want to use
     *
     * @return  A new date time of which time-zone is changed
     *
     * @throws  DateTimeException   if the result exceeds the supported date range
     */
    fun convertTimezoneTo(dateTime: LocalDateTime?, zoneIdTo: ZoneId?): LocalDateTime {
        return convertTimezoneFromTo(dateTime, ZoneId.systemDefault(), zoneIdTo)
    }

    /**
     *
     * Convert the time-zone of a date time from specific time-zone to system default
     *
     * @param   dateTime    A date time which you want to change time-zone
     * @param   zoneIdFrom  A Id. of time-zone which date time has originally
     *
     * @return  A new date time of which time-zone is changed
     *
     * @throws  DateTimeException   if the result exceeds the supported date range
     */
    fun convertTimezoneFrom(dateTime: LocalDateTime?, zoneIdFrom: ZoneId?): LocalDateTime {
        return convertTimezoneFromTo(dateTime, zoneIdFrom, ZoneId.systemDefault())
    }

    /**
     *
     * Convert the time-zone of a date time from specific time-zone to another
     *
     * @param   dateTime    A date time which you want to change time-zone
     * @param   zoneIdFrom  A Id. of time-zone which date time has originally
     * @param   zoneIdTo    A Id. of time-zone which you want to use
     *
     * @return  A new date time of which time-zone is changed
     *
     * @throws  DateTimeException   if the result exceeds the supported date range
     */
    fun convertTimezoneFromTo(dateTime: LocalDateTime?, zoneIdFrom: ZoneId?, zoneIdTo: ZoneId?): LocalDateTime {
        var zonedDateTime = ZonedDateTime.of(dateTime, zoneIdFrom)
        zonedDateTime = zonedDateTime.withZoneSameInstant(zoneIdTo)
        return zonedDateTime.toLocalDateTime()
    }

    /**
     *
     * Get an epoch time value in millisecond of current date time.
     *
     * @return  An epoch time value in millisecond of current date time.
     */
    val currentEpochTime: Long
        get() = getEpochTime(LocalDateTime.now())

    /**
     *
     * Get an epoch time value in millisecond of a specific date time.
     *
     * @param   time    A date time which you want to get epoch time value of.
     *
     * @return  An epoch time value in millisecond of a specific date time.
     */
    fun getEpochTime(time: LocalDateTime): Long {
        return getEpochTime(time, ZoneId.systemDefault())
    }

    /**
     *
     * Get an epoch time value in millisecond of a specific date time.
     *
     * @param   time    A date time which you want to get epoch time value of.
     *
     * @return  An epoch time value in millisecond of a specific date time.
     */
    fun getEpochTime(time: LocalDateTime, zoneId: ZoneId?): Long {
        return time.atZone(zoneId).toInstant().toEpochMilli()
    }
    /**
     *
     * Get a date time from an epoch time in millisecond.
     *
     * @param   epochTime
     *
     * @return  A converted date time from epoch time.
     */
    /**
     *
     * Get a date time from an epoch time in millisecond.
     *
     * @param   epochTime
     *
     * @return  A converted date time from epoch time.
     */
    @JvmOverloads
    fun fromEpochTime(epochTime: Long, zoneId: ZoneId? = ZoneId.systemDefault()): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochTime), zoneId)
    }

    /**
     *
     * Convert time data type from LocalDateTime type to Date type.
     *
     * @param   localDateTime   The original date-time
     *
     * @return  A converted date.
     */
    fun convertToDate(localDateTime: LocalDateTime): Date {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
    }

    /**
     *
     * Convert time data type from LocalDateTime type to Date type.
     *
     * @param   localDateTime   The original date-time
     *
     * @return  A converted date.
     */
    fun convertToDate(localDateTime: LocalDateTime, zoneId: ZoneId?): Date {
        return Date.from(localDateTime.atZone(zoneId).toInstant())
    }
}
