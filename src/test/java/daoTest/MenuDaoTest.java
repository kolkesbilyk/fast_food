package daoTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import com.fastfood.fastfood.config.ConfigServiceImpl;
import com.fastfood.fastfood.dao.MenuDAO;
import com.fastfood.fastfood.entity.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.fastfood.fastfood")
public class MenuDaoTest {

    private MenuDAO menuDAO;
    @Inject
    private ConfigServiceImpl configService;

    @BeforeEach
    public void init(){
        menuDAO = new MenuDAO();
    }

    @Test
    public void testMenuDao(){
        List<Menu> menuList = menuDAO.getMenuList();
        System.out.println(menuList);
    }

    @Test
    public void testProperties() throws IOException {
        File file = new File("application.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        for (String key: properties.stringPropertyNames()){
            System.out.println(properties.get(key));
        }
    }

    @Test
    public void tsetProperties(){
        System.out.println(configService.getProperties());
    }
}
