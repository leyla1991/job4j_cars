package ru.job4j.cars.model;

import lombok.*;
import org.hibernate.type.LocalDateTimeType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "price_history")
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private long before;
    private long after;
    private LocalDateTime created;

}
