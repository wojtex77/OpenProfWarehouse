package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialShapeTest {

    public static Shape shape;

    @BeforeAll
    public static void createShape() {
        shape = new SpecialShape("C-schiene", 20D);
    }

    @Test
    void generateName() {
        //given + when in beforeAll

        //then
        assertThat(shape.generateName()).isEqualTo("C-schiene");

    }

    @Test
    void getArea() {
        //given + when in beforeAll

        //then
        assertThat(shape.getArea()).isEqualTo(20D);
    }
}