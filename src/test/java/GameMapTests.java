import by.itclass.game.core.Cell;
import by.itclass.game.core.GameMap;
import org.junit.Assert;
import org.junit.Test;

public class GameMapTests {

    @Test
    public void testMapCreation(){
        GameMap map = new GameMap(10,10,null);
        Assert.assertNotNull(map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapCreationIllegalWidth(){
        GameMap map = new GameMap(-10,10,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapCreationIllegalHeight(){
        GameMap map = new GameMap(10,-10,null);
    }

    @Test
    public void testMapWidthGetter(){
        GameMap map = new GameMap(20,30,null);
        int actual = map.getWidth();
        int expected = 20;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testMapHeightGetter(){
        GameMap map = new GameMap(20,30,null);
        int actual = map.getHeight();
        int expected = 30;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testRightCellGetter(){
        GameMap map = new GameMap(5,5,null);
        Cell cell = map.getCell(3,3);
        Assert.assertNotNull(cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellGetterJNegative(){
        GameMap map = new GameMap(5,5,null);
        Cell cell = map.getCell(3,-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellGetterINegative(){
        GameMap map = new GameMap(5,5,null);
        Cell cell = map.getCell(-3,3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellGetterJTooBig(){
        GameMap map = new GameMap(5,5,null);
        Cell cell = map.getCell(3,7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellGetterITooBig(){
        GameMap map = new GameMap(5,5,null);
        Cell cell = map.getCell(9,3);
    }

    @Test
    public void testRightCellSetter(){
        GameMap map = new GameMap(7,7,null);
        Cell expected = new Cell(19);

        map.setCell(1,5,expected);//тестируем данный метод
        Cell actual = map.getCell(1,5);
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellSetterJNegative(){
        GameMap map = new GameMap(5,5,null);
        Cell cell = new Cell(2);
        map.setCell(3,-3,cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellSetterINegative(){
        GameMap map = new GameMap(5,5,null);
        Cell cell = new Cell(2);
        map.setCell(-3,3,cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellSetterJTooBig(){
        GameMap map = new GameMap(5,5,null);
        Cell cell = new Cell(2);
        map.setCell(3,8,cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellSetterITooBig(){
        GameMap map = new GameMap(5,5,null);
        Cell cell = new Cell(2);
        map.setCell(9,3,cell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellSetterNullCell(){
        GameMap map = new GameMap(5,5,null);
        Cell cell = null;
        map.setCell(3,3,cell);
    }
}
