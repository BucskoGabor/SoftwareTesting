package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TableTest {

    private Table fixedTable;
    private Table adjustableTable;

    @BeforeEach
    void setUp() {
        fixedTable = new Table(120, 180, 75, "brown", 4);
        adjustableTable = new Table(120, 180, 75, 100, "white", 4);
    }

    @Test
    void constructorWithoutCurrentHeightIsNotAdjustable() {
        assertFalse(fixedTable.isAdjustable());
        assertEquals(75, fixedTable.getCurrentHeight());
    }

    @Test
    void constructorWithCurrentHeightIsAdjustable() {
        assertTrue(adjustableTable.isAdjustable());
        assertEquals(100, adjustableTable.getCurrentHeight());
    }

    @Test
    void setHeightChangesCurrentHeight() {
        adjustableTable.setHeight(150);

        assertEquals(150, adjustableTable.getCurrentHeight());
    }

    @Test
    void setHeightAcceptsBoundaries() {
        adjustableTable.setHeight(0);
        assertEquals(0, adjustableTable.getCurrentHeight());

        adjustableTable.setHeight(200);
        assertEquals(200, adjustableTable.getCurrentHeight());
    }

    @Test
    void setHeightBelowZeroThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> adjustableTable.setHeight(-1)
        );
        assertEquals(100, adjustableTable.getCurrentHeight());
    }

    @Test
    void setHeightAbove200ThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> adjustableTable.setHeight(201)
        );
        assertEquals(100, adjustableTable.getCurrentHeight());
    }

    @Test
    void setHeightOnFixedTableThrowsException() {
        assertThrows(
                IllegalStateException.class,
                () -> fixedTable.setHeight(100)
        );
        assertEquals(75, fixedTable.getCurrentHeight());
    }

    @Test
    void areaIsWidthTimesLength() {
        assertEquals(21600, fixedTable.area());
    }

    @Test
    void capacityIsOnePersonPer60Cm() {
        assertEquals(10, fixedTable.getCapacity());
    }

    @Test
    void capacityIsRoundedDown() {
        Table table = new Table(100, 100, 75, "brown", 4);

        assertEquals(6, table.getCapacity());
    }

    @Test
    void repaintChangesColor() {
        fixedTable.repaint("black");

        assertEquals("black", fixedTable.getColor());
    }

    @Test
    void tableWithThreeLegsIsStable() {
        Table table = new Table(120, 180, 75, "brown", 3);

        assertTrue(table.isStable());
    }

    @Test
    void tableWithTwoLegsIsNotStable() {
        Table table = new Table(120, 180, 75, "brown", 2);

        assertFalse(table.isStable());
    }

    @Test
    void adjustableTableWithFourLegsIsFoldable() {
        assertTrue(adjustableTable.isFoldable());
    }

    @Test
    void fixedTableIsNotFoldable() {
        assertFalse(fixedTable.isFoldable());
    }

    @Test
    void adjustableTableWithThreeLegsIsNotFoldable() {
        Table table = new Table(120, 180, 75, 100, "white", 3);

        assertFalse(table.isFoldable());
    }

    @Test
    void perimeterIsTwiceWidthPlusLength() {
        assertEquals(600, fixedTable.getPerimeter());
    }
}
