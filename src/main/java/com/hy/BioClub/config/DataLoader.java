package com.hy.BioClub.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hy.BioClub.model.Question;
import com.hy.BioClub.repository.QuestionRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedQuestions(QuestionRepository questions) {
        return args -> {
            if (questions.count() > 0) {
                return;
            }
            List<Question> list = new ArrayList<>();
            // Generate 200 level-2 style biology review questions (varied topics)
            String[] topics = new String[]{"cells", "genetics", "ecology", "evolution", "photosynthesis", "respiration", "enzymes", "microorganisms", "homeostasis", "human physiology", "reproduction", "plant structure", "classification", "biotechnology", "ecology interactions", "energy flow", "populations", "ecosystems", "DNA structure", "cell division"};
            for (int i = 1; i <= 200; i++) {
                String topic = topics[i % topics.length];
                String q = makeQuestion(i, topic);
                list.add(new Question(q));
            }
            questions.saveAll(list);
            System.out.println("Seeded " + list.size() + " review questions.");
        };
    }

    private static String makeQuestion(int i, String topic) {
        // Create a varied Level 2 style question phrasing
        String base = switch (topic) {
            case "cells" ->
                "Describe the major differences between plant and animal cells and give one example of a specialised cell in each.";
            case "genetics" ->
                "Explain how dominant and recessive alleles affect phenotype using a simple monohybrid cross example.";
            case "ecology" ->
                "Describe how energy flows through a food chain and explain why energy is lost between trophic levels.";
            case "evolution" ->
                "Explain natural selection and give an example of a trait that could be selected for in a changing environment.";
            case "photosynthesis" ->
                "Describe the main inputs and outputs of photosynthesis and explain the role of chlorophyll.";
            case "respiration" ->
                "Compare aerobic and anaerobic respiration in terms of reactants, products and energy yield.";
            case "enzymes" ->
                "Explain how temperature and pH affect enzyme activity and describe an experiment to test this.";
            case "microorganisms" ->
                "Outline the beneficial and harmful roles of microorganisms in the environment and human health.";
            case "homeostasis" ->
                "Describe one example of homeostasis in humans and explain the feedback mechanisms involved.";
            case "human physiology" ->
                "Explain the pathway of oxygen from the atmosphere to body cells and how it is transported in the blood.";
            case "reproduction" ->
                "Compare sexual and asexual reproduction and outline one advantage of sexual reproduction.";
            case "plant structure" ->
                "Describe the structure of a leaf and explain how its features are suited to gas exchange and photosynthesis.";
            case "classification" ->
                "Explain the benefits of classifying organisms and give an example of how organisms are grouped.";
            case "biotechnology" ->
                "Describe one biotechnology technique and its application in medicine or agriculture.";
            case "ecology interactions" ->
                "Explain competition and predation and describe how they can affect population sizes.";
            case "energy flow" ->
                "Describe how sunlight is converted into chemical energy in ecosystems and why only a fraction is transferred to consumers.";
            case "populations" ->
                "Explain factors that can cause population numbers to increase or decrease in an ecosystem.";
            case "ecosystems" ->
                "Describe the components of an ecosystem and explain the importance of nutrient cycling.";
            case "DNA structure" ->
                "Describe the structure of DNA and explain how base pairing leads to replication fidelity.";
            case "cell division" ->
                "Outline the stages of mitosis and explain why cell division is important for growth and repair.";
            default ->
                "Describe an important biological concept related to " + topic + ".";
        };
        return String.format("%03d. %s", i, base + "");
    }
}
