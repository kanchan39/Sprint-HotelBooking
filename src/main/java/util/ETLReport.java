package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ETLReport {

    private static final Logger logger = LogManager.getLogger(ETLReport.class);

    public static void printReport(
            int total,
            int duplicates,
            int valid,
            int invalid,
            long time) {

        logger.info("========== ETL REPORT ==========");
        logger.info("Total Records Read      : {}", total);
        logger.info("Duplicates Removed      : {}", duplicates);
        logger.info("Valid Records           : {}", valid);
        logger.info("Invalid Records         : {}", invalid);
        logger.info("Execution Time (ms)     : {}", time);
        logger.info("================================");
    }
}