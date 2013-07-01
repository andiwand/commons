package at.andiwand.commons.math.geometry;

import at.andiwand.commons.math.matrix.Matrix3d;

public abstract class GeometryPointObject2D extends GeometryObject2D {

    public GeometryPointObject2D() {
    }

    public abstract GeometryPointObject2D transform(Matrix3d transform);

}