package com.delivery.delivery.tracking.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryStatusTest {

    @Test
    void draft_canChangeToWaitingForCourier() {
        assertTrue(DeliveryStatus.DRAFT.canChangeTo(DeliveryStatus.WAITING_FOR_COURIER));
    }

    @Test
    void  draft_canChangeToInTransit() {
        assertTrue(DeliveryStatus.DRAFT.canNotChangeTo(DeliveryStatus.IN_TRANSIT));
    }

    @Test
    void  draft_canNotChangeToWaitingForCourier() {
        assertTrue(DeliveryStatus.IN_TRANSIT.canNotChangeTo(DeliveryStatus.WAITING_FOR_COURIER));
    }

    @Test
        void  Delivery_canNotChangeToDelivery() {
        assertFalse(DeliveryStatus.DELIVERED.canChangeTo(DeliveryStatus.IN_TRANSIT));
    }
}