package pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive(message = "Amount must be positive")
    @Column(nullable = false)
    private BigDecimal amount;

    @NotNull(message = "Payment method is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @NotBlank(message = "Transaction number is required")
    @Column(nullable = false)
    private String transactionNumber;

    @NotBlank(message = "Donor name is required")
    @Column(nullable = false)
    private String donorName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Campos adicionales para campañas (NO se persistirán si no están mapeados con @Column)
    @Transient
    private String type;

    @Transient
    private String title;

    @Transient
    private String description;

    @Transient
    private String contactInfo;

    @Transient
    private String imageUrl;

    public Donation() {
        // JPA requires a no-args constructor
    }

    public Donation(BigDecimal amount, PaymentMethod paymentMethod, String transactionNumber, String donorName) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionNumber = transactionNumber;
        this.donorName = donorName;
        this.createdAt = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public String getDonorName() {
        return donorName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    // --- Métodos nuevos para campañas ---

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
