package com.recruiter.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationData implements Serializable {
    private String id;
    private String category;
    private Long timestamp;
    private String title;
    private String message;
}
