package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ItemUtilTest {
    @Test
    public void yieldId_withoutIdAtFirst_doFromZeroToAHundred() {
        for (int i = 0; i < 100; i++) {
            assertEquals(i, ItemUtil.yieldId("yieldIdTest"));
        }
    }

    @Test
    public void setBaseId_legalValues_success() {
        ItemUtil.setBaseIdOfItemType("setBaseIdTest", 1);
        assertEquals(1, ItemUtil.yieldId("setBaseIdTest"));
        assertEquals(2, ItemUtil.yieldId("setBaseIdTest"));
        ItemUtil.setBaseIdOfItemType("setBaseIdTest", 1000000);
        assertEquals(1000000, ItemUtil.yieldId("setBaseIdTest"));
    }

    @Test
    public void setBaseId_illegalValues_throwsIllegalArgumentException() {
        // Plain new item
        assertThrows(IllegalArgumentException.class, "The id value cannot be negative.", () ->
                ItemUtil.setBaseIdOfItemType("setBaseIdTest1", -1));

        // Existing item
        ItemUtil.setBaseIdOfItemType("setBaseIdTest", 1);
        ItemUtil.yieldId("setBaseIdTest");
        assertThrows(IllegalArgumentException.class, "The id value cannot be negative.", () ->
                ItemUtil.setBaseIdOfItemType("setBaseIdTest1", -10000));
    }
}
