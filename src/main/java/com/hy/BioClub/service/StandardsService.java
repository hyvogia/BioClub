package com.hy.BioClub.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hy.BioClub.model.StandardCard;

public class StandardsService {
    private static final List<StandardCard> STANDARDS = new ArrayList<>();

    static {
        STANDARDS.add(new StandardCard("Standard 91153",
                "Carry out a practical investigation in a biology context, with supervision.", "/pdfs/as91153.pdf"));
        STANDARDS.add(new StandardCard("Standard 91154",
                "Analyse the biological validity of information presented to the public.", "/pdfs/as91154.pdf"));
        STANDARDS.add(new StandardCard("Standard 91155",
                "Demonstrate understanding of adaptation of plants or animals to their way of life.", "/pdfs/as91155.pdf"));
        STANDARDS.add(new StandardCard("Standard 91158",
                "Investigate a pattern in an ecological community, with supervision.", "/pdfs/as91158.pdf"));
        STANDARDS.add(new StandardCard("Stadard 91160",
                "Investigate biological material at the microscopic level.", "/pdfs/as91160.pdf"));
    }

    public static List<StandardCard> getStandards() {
        return Collections.unmodifiableList(STANDARDS);
    }

    public static void add(StandardCard s) {
        STANDARDS.add(s);
    }

        public static StandardCard get(int index) {
                return STANDARDS.get(index);
        }

        public static void update(int index, StandardCard s) {
                STANDARDS.set(index, s);
        }

                public static void deleteAll() {
                        STANDARDS.clear();
                }

                                public static void delete(int index) {
                                        STANDARDS.remove(index);
                                }
}
