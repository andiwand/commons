package at.stefl.commons.math.geometry;

import java.util.HashSet;
import java.util.Set;

public abstract class GeometryPointLineObjectIntersection2D<G1 extends GeometryPointObject2D, G2 extends GeometryLineObject2D, P extends GeometryPointObject2D>
        extends
        GeometryIntersection2D<G1, G2, P, GeometryLineObject2D, GeometryAreaObject2D> {
    
    public GeometryPointLineObjectIntersection2D(G1 geometryPointObject,
            G2 geometryLineObject) {
        super(geometryPointObject, geometryLineObject);
    }
    
    protected final Set<GeometryLineObject2D> calcIntersectionLinesImpl() {
        return new HashSet<GeometryLineObject2D>();
    }
    
    protected final Set<GeometryAreaObject2D> calcIntersectionAreasImpl() {
        return new HashSet<GeometryAreaObject2D>();
    }
    
}