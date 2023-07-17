package daoTest;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public class DeploymentFactory {
    public static JavaArchive createDeployment(boolean recursive, String... packages){
        return ShrinkWrap.create(JavaArchive.class)
            .addAsManifestResource("META-INF/beans.xml")
            .addAsResource("application.properties")
            .addPackages(recursive, packages);
    }
}
