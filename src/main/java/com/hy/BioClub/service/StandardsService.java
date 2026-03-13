package com.hy.BioClub.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hy.BioClub.model.StandardCard;

@Service
public class StandardsService {

    private final List<StandardCard> standards = new ArrayList<>();

    public StandardsService() {
        standards.add(new StandardCard("Standard 91153",
                "Carry out a practical investigation in a biology context, with supervision.", "/pdfs/as91153.pdf"));
        standards.add(new StandardCard("Standard 91154",
                "Analyse the biological validity of information presented to the public.", "/pdfs/as91154.pdf"));
        standards.add(new StandardCard("Standard 91155",
                "Demonstrate understanding of adaptation of plants or animals to their way of life.", "/pdfs/as91155.pdf"));
        standards.add(new StandardCard("Standard 91158",
                "Investigate a pattern in an ecological community, with supervision.", "/pdfs/as91158.pdf"));
        standards.add(new StandardCard("Stadard 91160",
                "Investigate biological material at the microscopic level.", "/pdfs/as91160.pdf"));
    }

    public List<StandardCard> getStandards() {
        return Collections.unmodifiableList(standards);
    }

    public void add(StandardCard s) {
        standards.add(s);
    }

    public StandardCard get(int index) {
        return standards.get(index);
    }

    public void update(int index, StandardCard s) {
        standards.set(index, s);
    }

    public void deleteAll() {
        standards.clear();
    }

    public void delete(int index) {
        standards.remove(index);
    }
}
