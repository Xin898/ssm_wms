package com.xin.wms.util;

/*
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger("ExceptionLogging");

    /**
     * 捕获并记录Controller层抛出的非BusinessException异常

    @ExceptionHandler(value = Exception.class)
    public void handleException(Exception e){
        if (!(e instanceof BusinessException)){
            if (logger.isErrorEnabled()){
                StringBuilder builder = new StringBuilder();
                builder.append("cause:").append(e.getMessage());
                builder.append("\n\tstackTrack:\n");
                for (StackTraceElement stack : e.getStackTrace()) {
                    builder.append("\t\t");
                    builder.append(stack.toString());
                    builder.append("\n");
                }
                logger.error(builder.toString());
            }
        }
    }
}
*/
