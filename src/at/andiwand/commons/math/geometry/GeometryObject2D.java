package at.andiwand.commons.math.geometry;

import at.andiwand.commons.math.matrix.Matrix3d;

public abstract class GeometryObject2D {

    public GeometryObject2D() {
    }

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract GeometryObject2D transform(Matrix3d transform);

}