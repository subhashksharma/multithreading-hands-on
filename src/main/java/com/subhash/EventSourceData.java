package com.subhash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class EventSourceData {
    private String eventId;
    private String eventName;
    private String eventData;
    private Date eventSentDate;

}
