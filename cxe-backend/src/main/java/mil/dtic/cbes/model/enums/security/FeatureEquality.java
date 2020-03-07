package mil.dtic.cbes.model.enums.security;

public enum FeatureEquality {
    GTEQ, EQ;
    
    public static final FeatureEquality GREATER = GTEQ;
    public static final FeatureEquality EQUAL= EQ;
    
    public boolean isGreaterThanOrEqual() {
        return this == GTEQ;
    }
    
    public boolean isEqualTo() {
        return this == EQ;
    }
    
    

}
