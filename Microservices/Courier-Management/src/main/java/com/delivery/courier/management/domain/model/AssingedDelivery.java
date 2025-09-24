package com.delivery.courier.management.domain.model;

import lombok.*;

import javax.swing.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode
public class AssingedDelivery {

    @EqualsAndHashCode.Include
    private UUID id;

    private OffsetDateTime assignedAt;

    static AssingedDelivery pending(UUID deiveryId) {
        AssingedDelivery delivery = new AssingedDelivery();
        delivery.setId(deiveryId);
        delivery.setAssignedAt(OffsetDateTime.now());
        return delivery;
    }
}
