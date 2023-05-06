package com.recruiter.mapper;

import com.recruiter.model.NotificationData;
import com.recruiter.model.NotificationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationDataMapper {

    NotificationData toNotificationData(NotificationRequest notificationRequest);
}
