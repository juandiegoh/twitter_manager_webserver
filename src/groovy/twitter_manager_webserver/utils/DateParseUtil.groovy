package twitter_manager_webserver.utils

import java.text.SimpleDateFormat

class DateParseUtil {

    private static final def PATTERN = "dd-MM-yyyy HH:mm:ss"

    static def createDateFrom(date) {
        SimpleDateFormat sf = new SimpleDateFormat(PATTERN)
        sf.setLenient(true)
        return sf.parse(date)
    }

    static def getStringFromDate(Date date) {
        return date.format(PATTERN)
    }
}
