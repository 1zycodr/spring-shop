package kz.iitu.itse1909.amirlan.kernel.socket;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SocketMessage {
    private String from;
    private String text;
    private String time;
}
