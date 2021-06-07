import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RIPEMD320Test {

    @Test
    public void testPDFExamples() {
        Assertions.assertEquals(
                "22d65d5661536cdc75c1fdf5c6de7b41b9f27325ebc61e8557177d705a0ec880151c3a32a00899b8",
                RIPEMD320.hash(""));
        System.out.println(RIPEMD320.hash(""));

        Assertions.assertEquals(
                "de4c01b3054f8930a79d09ae738e92301e5a17085beffdc1b8d116713e74f82fa942d64cdbc4682d",
                RIPEMD320.hash("abc"));
    }

    @Test
    public void testOnlineCalculator() {
        Assertions.assertEquals(
                "9fb0b39305b4b6a8e2c70f594a212f32ce3d47dbd07a74bfa9986ac2a917d14b083fe9bf67616ea3",
                RIPEMD320.hash("Nastya"));

        Assertions.assertEquals(
                "dca3242f6fc0abfed780a48e7fbba578d663933c5067fbe1d83100cf92de391e6c147e82a88fa563",
                RIPEMD320.hash("I like crypto"));
    }
}
