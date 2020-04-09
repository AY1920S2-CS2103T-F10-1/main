package seedu.address.model.item.field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.ResumeBuilder;
import seedu.address.testutil.SkillBuilder;

public class NameContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("AWS"));
        assertTrue(predicate.test(new InternshipBuilder().withName("AWS Software Engineering").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("UX", "UI"));
        assertTrue(predicate.test(new SkillBuilder().withName("UX UI").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Orbital", "Mobile"));
        assertTrue(predicate.test(new ProjectBuilder().withName("Orbital Project").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("reSuMe", "soFtwaRe"));
        assertTrue(predicate.test(new ResumeBuilder().withName("Software Engineering Resume").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ProjectBuilder().withName("Orbital").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Github"));
        assertFalse(predicate.test(new SkillBuilder().withName("Adobe Photoshop").build()));

        // Keywords match role, from, to and description, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("intern", "05-2020", "08-2020", "helloImHai"));
        assertFalse(predicate.test(new InternshipBuilder().withName("Ninja Van").withRole("intern")
                .withFrom("05-2020").withTo("08-2020").withDescription("helloImHai").build()));
    }

}
