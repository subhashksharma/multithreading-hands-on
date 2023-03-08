package com.subhash.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;


import static java.lang.Thread.sleep;

public class CommonUtil {
    public static final Logger log =  LoggerFactory.getLogger(CommonUtil.class);
    public static StopWatch stopWatch = new StopWatch();
    public static void delay(long delayInMilliseconds) {
        try{
            sleep(delayInMilliseconds);
        }catch(Exception e) {
            log.info("Exception in the "+ e.getMessage());
        }
    }

}
