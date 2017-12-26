import by.itclass.game.core.Cell;
import org.junit.Assert;
import org.junit.Test;

public class CellTests {

    @Test
    public void testCellCreation(){
        Cell actual = new Cell(1);
        Assert.assertNotNull(actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellInvalidType(){
        Cell actual = new Cell(-10);
    }

    @Test
    public void testCellTypeGetter(){
        Cell cell = new Cell(3);
        int actual = cell.getType();
        int expected = 3;
        Assert.assertEquals(expected,actual);
    }
}
