package com.github.countrybros.model.event;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class TimeInterval {

    private LocalDateTime start;

    /**
     * cordially,
     * MA VAFFANCULO SPRING
     */
    private LocalDateTime endTime;

    public TimeInterval() {}

    public LocalDateTime getStart() { return start; }
    public void setStart(LocalDateTime start) { this.start = start; }

    public LocalDateTime getEnd() { return endTime; }
    public void setEnd(LocalDateTime end) { this.endTime = end; }
}
