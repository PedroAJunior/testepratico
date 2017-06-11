package testepratico;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProdutoRepositoryTest.class,
        ImagemRepositoryTest.class
})

@SpringBootApplication
public class DataApplicationTests {
    @Test
    public void contextLoads() {
    }
}
