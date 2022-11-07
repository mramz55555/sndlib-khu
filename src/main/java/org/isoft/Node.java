package org.isoft;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Node {
    @XmlAttribute
    private String id;
    @XmlElement
    private Coordinates coordinates;

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Coordinates {
        @XmlElement
        private String x;
        @XmlElement
        private String y;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", coordinates=" + coordinates +
                "}\n";
    }
}