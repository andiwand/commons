package at.andiwand.commons.math.geometry;

import at.andiwand.commons.math.matrix.Matrix3d;

public abstract class GeometryLineObject2D extends GeometryObject2D {

    public GeometryLineObject2D() {
    }

    public abstract GeometryLineObject2D transform(Matrix3d transform);

}