package daoTest;

import java.util.List;

import com.fastfood.fastfood.dao.DishDAO;
import com.fastfood.fastfood.dao.OrderDAO;
import com.fastfood.fastfood.entity.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DishDaoTest {

    private DishDAO dishDAO;
    private OrderDAO orderDAO;

    @BeforeEach
    public void init(){
        dishDAO = new DishDAO();
        orderDAO = new OrderDAO();
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void getDishesTest(int menu){
        List<Dish> dishList = dishDAO.getDishesByMenuId(menu);
        System.out.println(dishList);
    }

    @Test
    public void order(){
        System.out.println(orderDAO.getOrderById(4));
    }
}
