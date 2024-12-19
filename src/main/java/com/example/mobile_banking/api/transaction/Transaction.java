package com.example.mobile_banking.api.transaction;
import com.example.mobile_banking.api.account.Account;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique = true, nullable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "sender_act_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_act_id")
    private Account  receiver;

    private BigDecimal amount;
    private String remark;
    private  Boolean isPayment;
    private String studentCardNo;
    private LocalDateTime transactionAt;
}
