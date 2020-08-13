package com.github.tunabel.sbmsbeerservice.web.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    Timestamp toTimestamp(OffsetDateTime offsetDateTime) {
        if(offsetDateTime != null) {
            return Timestamp.valueOf(offsetDateTime.toLocalDateTime());
        } else  {
            return null;
        }
    }

    OffsetDateTime asOffsetDateTime(Timestamp ts) {
        if (ts != null) {
            return OffsetDateTime.of(ts.toLocalDateTime(), ZoneOffset.UTC);
        } else {
            return null;
        }
    }

}
