package io.github.onecx.product.store.domain.models;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import org.tkit.quarkus.jpa.models.TraceableEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PS_MICROFRONTEND", uniqueConstraints = {
        @UniqueConstraint(name = "PS_MICROFRONTEND_UNIQUE", columnNames = { "REMOTE_ENTRY", "REMOTE_BASE_URL",
                "EXPOSED_MODULE" }),
        @UniqueConstraint(name = "PS_MICROFRONTEND_APP_ID", columnNames = { "PRODUCT_NAME", "APP_ID" })
}, indexes = {
        @Index(name = "PS_MICROFRONTEND_PRODUCT_NAME", columnList = "PRODUCT_NAME")
})
@NamedEntityGraph(name = Microfrontend.MICROFRONTEND_LOAD, includeAllAttributes = true)
@SuppressWarnings("java:S2160")
public class Microfrontend extends TraceableEntity {
    public static final String MICROFRONTEND_LOAD = "MICROFRONTEND_LOAD";

    @Column(name = "APP_ID")
    private String appId;

    @Column(name = "APP_VERSION")
    private String appVersion;

    @Column(name = "APP_NAME")
    private String appName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REMOTE_BASE_URL")
    private String remoteBaseUrl;

    @Column(name = "REMOTE_ENTRY")
    private String remoteEntry;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "CLASSIFICATIONS")
    @ElementCollection(fetch = LAZY)
    @CollectionTable(name = "MFE_CLASSIFICATIONS")
    private Set<String> classifications;

    @Column(name = "CONTACT", columnDefinition = "TEXT")
    private String contact;

    @Column(name = "ICON_NAME")
    private String iconName;

    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @Column(name = "OPERATOR", nullable = false)
    private boolean operator;

    @Column(name = "EXPOSED_MODULE")
    private String exposedModule;

    @Column(name = "TECHNOLOGY")
    private String technology;

    @OneToMany(cascade = { REMOVE, REFRESH, PERSIST, MERGE }, fetch = LAZY, orphanRemoval = true)
    @JoinColumn(name = "MICROFRONTEND_ID")
    private Set<UIEndpoint> endpoints = new HashSet<>();
}
