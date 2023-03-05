package daoTest;

import javax.inject.Inject;

import com.fastfood.fastfood.dao.DishDAO;
import com.fastfood.fastfood.dao.MenuDAO;
import com.fastfood.fastfood.dao.OrderDAO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ArquillianExtension.class)
public class TestArquillian {

    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(MenuDAO.class)
            .addClass(DishDAO.class)
            .addClass(OrderDAO.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private DishDAO dishDAO;

    @Test
    public void test(){
        dishDAO.getAllDishes();
    }
}
