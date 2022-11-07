package org.isoft;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Demand {
    @XmlAttribute
    private String id;
    @XmlElement
    private String source;
    @XmlElement
    private String target;
    @XmlElement
    private String demandValue;

    @Override
    public String toString() {
        return "Demand{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", demandValue='" + demandValue + '\'' +
                "}\n";
    }
}
