package one.maistrenko.shop.idGenerator;

import org.springframework.stereotype.Component;

@Component("generator")
public class IdGeneratorImpl implements IdGenerator {
    private long id = 0;
    @Override
    public long generateId() {
            return id++;
    }
}
