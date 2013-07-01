package at.andiwand.commons.math.geometry;

import at.andiwand.commons.math.matrix.Matrix3d;

public abstract class GeometryAreaObject2D extends GeometryObject2D {

    public GeometryAreaObject2D() {
    }

    public abstract GeometryAreaObject2D transform(Matrix3d transform);

}