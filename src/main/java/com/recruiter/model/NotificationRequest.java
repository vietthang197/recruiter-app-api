package com.recruiter.model;

import lombok.Data;

@Data
public class NotificationRequest extends NotificationData {
    private String sessionId;
}
