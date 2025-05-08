package com.subs.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sub {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_seq")
    @SequenceGenerator(name = "subscription_seq", sequenceName = "subscriptions_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "subscription_time")
    private ZonedDateTime subscriptionTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
