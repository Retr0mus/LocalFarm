package com.github.countrybros.model.event;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class TimeInterval {

    private LocalDateTime startTime;

    /**
     * cordially,
     * MA VAFFANCULO SPRING
     */
    private LocalDateTime endTime;

    public TimeInterval() {}

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime start) { this.startTime = start; }

    public LocalDateTime getEnd() { return endTime; }
    public void setEnd(LocalDateTime end) { this.endTime = end; }
}
