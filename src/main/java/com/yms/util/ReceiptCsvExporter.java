package com.yms.util;

import java.io.PrintWriter;
import java.util.List;

import com.yms.entity.Receipt;

public class ReceiptCsvExporter {

    public static void writeToCsv(PrintWriter writer, List<Receipt> receipts) {

        writer.println("Receipt ID,Vehicle Number,Driver,Entry Time,Exit Time,Hours,Amount,Payment Status");

        for (Receipt r : receipts) {
            writer.printf(
                "%d,%s,%s,%s,%s,%d,%.2f,%s%n",
                r.getId(),
                r.getVehicleNumber(),
                r.getDriverName(),
                r.getEntryTime(),
                r.getExitTime(),
                r.getDurationInHours(),
                r.getTotalAmount(),
                r.getPaymentStatus()
            );
        }
    }
}