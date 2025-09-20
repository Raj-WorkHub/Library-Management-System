package com.library.service;


import com.library.model.Patron;
import com.library.repository.PatronRepository;
import com.library.util.LoggerUtil;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


public class PatronServiceImpl implements PatronService {
    private final PatronRepository repository;
    private final Logger logger = LoggerUtil.getLogger(PatronServiceImpl.class);


    public PatronServiceImpl(PatronRepository repository) { this.repository = repository; }


    @Override
    public void addPatron(Patron patron) {
        repository.save(patron);
        logger.info(() -> "Added patron: " + patron.getId());
    }


    @Override
    public void updatePatron(String id, String name, String contact) {
        repository.findById(id).ifPresent(p -> {
            p.setName(name);
            p.setContactInfo(contact);
            repository.save(p);
            logger.info(() -> "Updated patron: " + id);
        });
    }


    @Override
    public Optional<Patron> findById(String id) { return repository.findById(id); }


    @Override
    public List<Patron> listAll() { return repository.findAll(); }
}