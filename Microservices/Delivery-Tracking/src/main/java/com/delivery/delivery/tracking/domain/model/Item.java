package com.delivery.delivery.tracking.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
public class Item {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;

    @ManyToOne(optional = false)
    @Getter(AccessLevel.PACKAGE)
    private Delivery delivery;

    @Setter(AccessLevel.PACKAGE)
    private Integer quantity;

    static Item brandNew(String name, Integer quantity, Delivery delivery) {
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setName(name);
        item.setQuantity(quantity);
        item.setDelivery(delivery);
        return item;
    }
}
