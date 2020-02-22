package mil.dtic.cbes.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mil.dtic.cbes.model.enums.BooleanFlag;
import mil.dtic.cbes.model.enums.FeatureEquality;

@Entity
@Table(name="FEATURE_ACCESS")
public class FeatureEntity implements IEntity, Serializable {
    private static final long serialVersionUID = 8304316328690621767L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="FEATURE_ID")
    private Integer id;
    
    @Column(name="POINT_CUT")
    private String pointCut;
    
    @Column(name="FEATURE_QUAL")
    private Integer featureQual;
    
    @Column(name="EQUAL_LOGIC")
    private FeatureEquality equalLogic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPointCut() {
        return pointCut;
    }

    public void setPointCut(String pointCut) {
        this.pointCut = pointCut;
    }

    public Integer getFeatureQual() {
        return featureQual;
    }

    public void setFeatureQual(Integer featureQual) {
        this.featureQual = featureQual;
    }
    
    public FeatureEquality getEqualLogic() {
        return equalLogic;
    }

    public void setEqualLogic(FeatureEquality equalLogic) {
        this.equalLogic = equalLogic;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((equalLogic == null) ? 0 : equalLogic.hashCode());
        result = prime * result + ((featureQual == null) ? 0 : featureQual.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((pointCut == null) ? 0 : pointCut.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FeatureEntity other = (FeatureEntity) obj;
        if (equalLogic != other.equalLogic)
            return false;
        if (featureQual == null) {
            if (other.featureQual != null)
                return false;
        } else if (!featureQual.equals(other.featureQual))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (pointCut == null) {
            if (other.pointCut != null)
                return false;
        } else if (!pointCut.equals(other.pointCut))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FeatureEntity [id=" + id + ", pointCut=" + pointCut + ", featureQual=" + featureQual + ", equalLogic=" + equalLogic + "]";
    }
    

}
