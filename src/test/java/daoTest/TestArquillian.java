package daoTest;

import javax.inject.Inject;

import com.fastfood.fastfood.config.AppProperties;
import com.fastfood.fastfood.config.ConfigService;
import com.fastfood.fastfood.dao.DishDAO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ArquillianExtension.class})
public class TestArquillian {

    @Deployment
    public static JavaArchive createDeployment(){
        return DeploymentFactory.createDeployment(true, "com/fastfood/fastfood");
    }

    @Inject
    private DishDAO dishDAO;
    @Inject
    private ConfigService configService;

    @Test
    public void test(){
        System.out.println(dishDAO.getAllDishes());
    }
}
