package pl.wojciechsiwek.OpenProfWarehouse.materialShapes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.CircularBar;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.RectangularBar;
import pl.wojciechsiwek.OpenProfWarehouse.materialShapeTypes.Shape;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MaterialShapeServiceTest {

    private static MaterialShapeService service;

    @BeforeAll
    private static void setService(){
        service = new MaterialShapeService();
    }


    @Test
    void shouldGenerateMaterialShapeFromAnyShape() {
        //given
        Shape shape = new RectangularBar(20d, 10d);
        MaterialShape materialShape;

        //when
        materialShape = service.convertToMaterialShape(shape);

        //then
        assertThat(materialShape.getArea()).isEqualTo(shape.getArea());
        assertThat(materialShape.getName()).isEqualTo(shape.getName());

    }

    @Test
    void shouldReturnProperAreaForCircularBarFromSpecificName() {
        //given
        MaterialShape materialShape = new MaterialShape();
        materialShape.setName("PO-20.0");

        //when
        Shape shape = service.returnProperType(Optional.of(materialShape));

        //then
        assertThat(shape.getArea()).isEqualTo(15.707963267948966);
    }

    @Test
    void shouldReturnProperAreaForRectangularBarFromSpecificName() {
        //given
        MaterialShape materialShape = new MaterialShape();
        materialShape.setName("PP-5.0x8.0");


        //when
        Shape shape = service.returnProperType(Optional.of(materialShape));

        //then
        assertThat(shape.getArea()).isEqualTo(40);
    }


    @Test
    void shouldReturnProperAreaForRectangularTubeFromSpecificName() {
        //given
        MaterialShape materialShape = new MaterialShape();
        materialShape.setName("RP-60x40x2");


        //when
        Shape shape = service.returnProperType(Optional.of(materialShape));

        //then
        assertThat(shape.getArea()).isEqualTo(384);
    }


    @Test
    void shouldReturnProperAreaForCircularTubeFromSpecificName() {
        //given
        MaterialShape materialShape = new MaterialShape();
        materialShape.setName("RO-30x2");


        //when
        Shape shape = service.returnProperType(Optional.of(materialShape));

        //then
        assertThat(shape.getArea()).isEqualTo(175.92918860102841);
    }




    @Test
    void shouldReturnProperTemplateStringFromShape() {
        //given + when
        Shape shape = new CircularBar(20d);

        //when
        String name = service.getTemplate(shape);

        //then
        assertThat(name).isEqualTo("materialShapes/editCircularBar");
    }
}