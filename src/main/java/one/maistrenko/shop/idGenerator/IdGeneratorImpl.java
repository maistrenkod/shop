package one.maistrenko.shop.idGenerator;

public class IdGeneratorImpl implements IdGenerator {
    private long id = 0;
    @Override
    public long generateId() {
            return id++;
    }
}
