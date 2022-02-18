package fr.remy.cc1.projectTradesmen.domain;

public final class ValidationProjectTradesmenCandidateEngine {
    private static final ValidationProjectTradesmenCandidateEngine INSTANCE = new ValidationProjectTradesmenCandidateEngine();

    private ValidationProjectTradesmenCandidateEngine() {}

    public static ValidationProjectTradesmenCandidateEngine getInstance() {
        return INSTANCE;
    }

    public boolean test(ProjectTradesmenCandidate candidate) {
        return true;
    }
}
