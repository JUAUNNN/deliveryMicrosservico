package com.delivery.courier.management.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PRIVATE)
public class Courier {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Setter(AccessLevel.PUBLIC)
    private String name;

    @Setter(AccessLevel.PUBLIC)
    private String phone;

    private Integer fulfilledDeliveriesQuantity;

    private Integer pendingDeliveriesQuantity;

    private OffsetDateTime lastFulfilledDeliveryAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "courier")
    private List<AssingedDelivery> pendingDeliveries = new ArrayList<>();

    public List<AssingedDelivery> getPendingDeliveries() {
        return Collections.unmodifiableList(this.pendingDeliveries);
    }

    public static Courier brandNew(String name, String phone) {
       Courier courier = new Courier();
       courier.setName(name);
       courier.setPhone(phone);
       courier.setFulfilledDeliveriesQuantity(0);
       courier.setPendingDeliveriesQuantity(0);
       return courier;
    }

    public void assign(UUID deliveryId) {
        this.pendingDeliveries.add(
                AssingedDelivery.pending(deliveryId, this)
        );
        this.pendingDeliveriesQuantity ++;
    }

    public void fulfill(UUID deliveryId) {
        AssingedDelivery delivery = this.pendingDeliveries.stream().filter(
                d -> d.getId().equals(deliveryId)
        ).findFirst().orElseThrow();
        this.pendingDeliveries.remove(delivery);

        this.pendingDeliveriesQuantity --;
        this.fulfilledDeliveriesQuantity ++;
        this.lastFulfilledDeliveryAt = OffsetDateTime.now();
    }
}
