package pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class CircularTubeTest {

    private static Shape shape;

    @BeforeAll
    private static void createObject() {
        shape = new CircularTube(10, 1);
    }

    @Test
    void checkIfAreaProperlyCalculated() {
        //given + when in beforeAll

        //then
        assertThat(shape.getArea()).isEqualTo(Math.PI*(Math.pow(5, 2)-Math.pow(4, 2)));
    }

    @Test
    void generateName() {
        //given + when in beforeAll

        //then
        assertThat(shape.generateName()).isEqualTo("RO-10.0x1.0");

    }
}