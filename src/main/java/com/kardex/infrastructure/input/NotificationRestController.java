package com.kardex.infrastructure.input;

import com.kardex.application.dto.notificationDto.NotificationStatusResponse;
import com.kardex.application.dto.notificationDto.NotificationPaginated;
import com.kardex.application.handler.notificationHandler.INotificationHandler;
import com.kardex.domain.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.NOTIFICATIONS_BASE_PATH)
@RequiredArgsConstructor
public class NotificationRestController {
    private final INotificationHandler notificationHandler;

    @Operation(summary = "Add a new notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.CREATED, description = "Notification created", content = @Content),
            @ApiResponse(responseCode = Constants.CONFLICT, description = "Notification already exists", content = @Content)
    })
    @PostMapping(Constants.LOW_STOCK_PATH)
    public ResponseEntity<Void> saveProductNotification(@RequestParam String userId, @RequestParam String productName, @RequestParam String companyName) {
        notificationHandler.saveProductNotification(userId, productName, companyName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Add a new notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.CREATED, description = "Notification created", content = @Content),
            @ApiResponse(responseCode = Constants.CONFLICT, description = "Notification already exists", content = @Content)
    })
    @PostMapping(Constants.ORDER_UPDATE_PATH)
    public ResponseEntity<Void> saveOrderNotification(@RequestParam String userId, @RequestParam String productName,@RequestParam String orderStatus) {
        notificationHandler.saveOrderNotification(userId, productName, orderStatus);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get paginated list of notifications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Paged notifications returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<NotificationPaginated>> getNotifications(
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = Constants.DEFAULT_ASC) boolean asc) {
        Page<NotificationPaginated> notifications = notificationHandler.getAllNotifications(page, size, sortBy, asc);
        return ResponseEntity.ok(notifications);
    }

    @Operation(summary = "Get status notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Notifications found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationStatusResponse.class))),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "Notification not found", content = @Content)
    })
    @GetMapping(Constants.GET_NOTIFICATIONS_STATUS_PATH)
    public ResponseEntity<List<NotificationStatusResponse>> getNotificationStatus() {
        return ResponseEntity.ok(notificationHandler.getNotificationStatus());
    }

    @Operation(summary = "Update status product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Notification status updated successfully", content = @Content),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "Notification not found", content = @Content)
    })
    @PatchMapping(Constants.NOTIFICATION_STATUS_UPDATE) // Usamos la constante para el ID path
    public ResponseEntity<Void> updateStatusNotification(@Parameter(description = "ID of the product to be updated")
                                                      @PathVariable Long notificationId,
                                                      @RequestParam Long notificationStatusId) {
        notificationHandler.updateStatusNotification(notificationId, notificationStatusId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a notification by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Notification deleted successfully", content = @Content),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "Notification not found", content = @Content)
    })
    @DeleteMapping(Constants.NOTIFICATION_ID_PATH)
    public ResponseEntity<Void> deleteNotification(@Parameter(description = "ID of the notification to be deleted")
                                                       @PathVariable Long notificationId) {
        notificationHandler.deleteNotification(notificationId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete all notifications with status in read")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.OK, description = "Notifications deleted successfully", content = @Content),
            @ApiResponse(responseCode = Constants.NOT_FOUND, description = "Notifications not found", content = @Content)
    })
    @DeleteMapping()
    public ResponseEntity<Void> deleteAllReadNotifications() {
        notificationHandler.deleteAllReadNotifications();
        return ResponseEntity.noContent().build();
    }
}
