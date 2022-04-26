package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class RectangularTubeTest {

    private static Shape shape;

    @BeforeAll
    private static void createObject() {
        shape = new RectangularTube(10D, 10D,2.5D);
    }

    @Test
    void checkIfAreaProperlyCalculated() {
        //given + when in beforeAll

        //then
        assertThat(shape.getArea()).isEqualTo(75);
    }

    @Test
    void generateName() {
        //given + when in beforeAll

        //then
        assertThat(shape.generateName()).isEqualTo("RP-10.0x10.0x2.5");

    }
}