import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fbank.utils.HibernateUtils;

public class HibernateTest {
    @Test
    public void testHibernate(){
        Assertions.assertThat(HibernateUtils.getSession()).isNotNull();
    }
}
