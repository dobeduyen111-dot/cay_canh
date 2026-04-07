package ceb.service;

import ceb.model.Payments;
import ceb.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentsRepository paymentsRepo;

    public int pay(Payments p) {
        return paymentsRepo.create(p);
    }
}
